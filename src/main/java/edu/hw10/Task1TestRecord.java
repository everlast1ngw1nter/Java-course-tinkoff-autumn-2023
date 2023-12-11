package edu.hw10;

public record Task1TestRecord(@Task1Annotations.Max(3) @Task1Annotations.Min(2) int a, @Task1Annotations.NotNull String b) {
}
