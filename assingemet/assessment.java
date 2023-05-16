import java.util.Scanner;

public class AssemblyLanguage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the assembly language code from the user.
        String assemblyLanguageCode = scanner.nextLine();

        // Create an assembly language parser.
        AssemblyLanguageParser parser = new AssemblyLanguageParser(assemblyLanguageCode);

        // Parse the assembly language code.
        parser.parse();

        // Print the results of the assembly language code.
        System.out.println(parser.getResult());
    }
}

class AssemblyLanguageParser {

    private String assemblyLanguageCode;
    private String[] instructions;
    private int currentInstructionIndex;

    public AssemblyLanguageParser(String assemblyLanguageCode) {
        this.assemblyLanguageCode = assemblyLanguageCode;
        this.instructions = assemblyLanguageCode.split("\n");
        this.currentInstructionIndex = 0;
    }

    public void parse() {
        while (currentInstructionIndex < instructions.length) {
            parseInstruction();
        }
    }

    private void parseInstruction() {
        String instruction = instructions[currentInstructionIndex++];

        // Split the instruction into its parts.
        String[] parts = instruction.split(" ");

        // Check if the instruction is a valid instruction.
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid instruction: " + instruction);
        }

        // Get the operation code, the register, and the constant.
        String operationCode = parts[0];
        String register = parts[1];
        String constant = parts[2];

        // Execute the instruction.
        switch (operationCode) {
            case "MV":
                mv(register, constant);
                break;
            case "ADD":
                add(register, constant);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation code: " + operationCode);
        }
    }

    private void mv(String register, String constant) {
        // Load the value of the constant into the register.
        int value = Integer.parseInt(constant);
        System.out.println("Loading value " + value + " into register " + register);
    }

    private void add(String register, String constant) {
        // Add the value of the constant to the register.
        int value = Integer.parseInt(constant);
        int result = Integer.parseInt(register) + value;
        System.out.println("Adding value " + value + " to register " + register + ", resulting in " + result);
    }

    public String getResult() {
        return instructions[currentInstructionIndex - 1];
    }
}
