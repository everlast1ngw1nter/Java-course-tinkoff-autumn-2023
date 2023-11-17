package edu.hw6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

public class Task6 {

    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    private static final int MIN_PORT = 0;

    private static final int MAX_PORT = 49151;

    private static final Map<Integer, String> PORT_SERVICES = Map.ofEntries(
            entry(135, "EPMAP"),
            entry(137,  "Служба имен NetBIOS"),
            entry(138,  "Служба датаграмм NetBIOS"),
            entry(139,  "Служба сеансов NetBIOS"),
            entry(445,  "Microsoft-DS Active Directory"),
            entry(843,  "Adobe Flash"),
            entry(1900,  "Simple Service Discovery Protocol (SSDP)"),
            entry(3702,"Динамическое обнаружение веб-служб"),
            entry(5353,  "Многоадресный DNS"),
            entry(5355,  "Link-Local Multicast Name Resolution (LLMNR)")
    );


    public record PortInfo(Protocol protocol, boolean isBusy, Integer portNumber) {}

    private enum Protocol{
        UDP,
        TSP,
        NAN
    }

    private static PortInfo isPortBusy(int portNumber) {
        try (var datagramSocket = new DatagramSocket(portNumber)){
        }
        catch (IOException e) {
            return new PortInfo(Protocol.UDP, true, portNumber);
        }
        try (var socket = new ServerSocket(portNumber)) {
        }
        catch (IOException e) {
            return new PortInfo(Protocol.TSP, true, portNumber);
        }
        return new PortInfo(Protocol.NAN, false, portNumber);
    }

    private static List<PortInfo> getBusyPorts() {
        List<PortInfo> portInfos = new ArrayList<>();
        for (int port = MIN_PORT; port < MAX_PORT + 1; port++) {
            var portInfo = isPortBusy(port);
            if (portInfo.isBusy){
                portInfos.add(portInfo);
            }
        }
        return portInfos;
    }

    public static void printBusyPorts() {
        var appendBeforePortName = 8;
        var appendBeforePortService = 7;
        var busyPorts = getBusyPorts();
        LOGGER.info("Протокол   Порт   Сервис");
        for (var port : busyPorts) {
            var serviceName = PORT_SERVICES.getOrDefault(port.portNumber, "");
            LOGGER.info(
                    port.protocol
                    + " ".repeat(appendBeforePortName)
                    + port.portNumber
                    + " ".repeat(appendBeforePortService - port.portNumber.toString().length())
                            + serviceName);
        }
    }
}
