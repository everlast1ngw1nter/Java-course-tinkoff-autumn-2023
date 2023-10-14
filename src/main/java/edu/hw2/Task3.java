package edu.hw2;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Task3 {

    private Task3() {
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RND = new Random();

    private static boolean getRandomBool() {
        return RND.nextBoolean();
    }

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {
        @Override
        public void execute(String command) {
            LOGGER.info("StableConnection executed");
        }

        @Override
        public void close() throws ConnectionException {
            LOGGER.info("StableConnection closed");
        }
    }

    public static class FaultyConnection implements Connection {
        @Override
        public void execute(String command) {
            LOGGER.info("FaultyConnection executed");
        }

        @Override
        public void close() throws ConnectionException {
            if (getRandomBool()) {
                throw new ConnectionException();
            }
            LOGGER.info("FaultyConnection closed");
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            if (getRandomBool()) {
                return new StableConnection();
            }
            return new FaultyConnection();
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static class ConnectionException extends RuntimeException {

        public ConnectionException() {
        }

        public ConnectionException(Throwable source) {
            super(String.valueOf(source));
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) {
            try (Connection connection = manager.getConnection()) {
                for (int i = 0; i < maxAttempts; i++) {
                    connection.execute(command);
                }
            } catch (Exception exception) {
                throw new ConnectionException(exception.getCause());
            }
        }
    }
}
