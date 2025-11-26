package Grazioso.Animals;

public enum TrainingStatus {
    INTAKE,
    PHASE_I,
    PHASE_II,
    PHASE_III,
    IN_SERVICE;

    public static TrainingStatus fromString(String input) {
        return TrainingStatus.valueOf(input.trim().replace(" ", "_").toUpperCase());
    }
}
