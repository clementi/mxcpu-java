package net.jeffreypratt.mxcpu;

import java.util.Arrays;
import java.util.stream.Collectors;

class CpuState {
    private int cycles;
    private int inc;
    private int pc;
    private int acc;
    private int[] registers = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    int getCycles() {
        return this.cycles;
    }

    void incrementCycles() {
        this.cycles++;
    }

    int getInc() {
        return inc;
    }

    void setInc(int inc) {
        this.inc = inc;
    }

    void incrementInc() {
        this.inc++;
    }

    void decrementInc() {
        this.inc--;
    }

    int getPc() {
        return pc;
    }

    void setPc(int pc) {
        this.pc = pc;
    }

    void incrementPc() {
        this.pc++;
    }

    void incrementPcBy(int n) {
        this.pc += n;
    }

    int getAcc() {
        return acc;
    }

    void setAcc(int acc) {
        this.acc = acc;
    }

    void addToAcc(int n) {
        this.acc += n;
    }

    int[] getRegisters() {
        return registers;
    }

    @Override
    public String toString() {
        String registerValues = Arrays.stream(this.registers)
                .mapToObj(r -> "0x" + Integer.toHexString(r))
                .collect(Collectors.joining(", "));

        return String.format("Cycles    : %d%n", this.cycles) +
               String.format("INC       : 0x%02X%n", this.inc) +
               String.format("PC        : 0x%02X%n", this.pc) +
               String.format("ACC       : 0x%02X%n", this.acc) +
               String.format("Registers : [%s]", registerValues);
    }
}
