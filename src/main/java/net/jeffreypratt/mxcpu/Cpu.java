package net.jeffreypratt.mxcpu;

class Cpu {
    private CpuState state;

    Cpu(CpuState state) {
        this.state = state;
    }

    CpuState getState() {
        return this.state;
    }

    void run(int[] program) throws UnknownOpCodeException, LongRunTimeException {
        while (true) {
            if (this.state.getCycles() == 1000) {
                throw new LongRunTimeException("Program ran too long. Is there an infinite loop in your program?");
            }

            this.state.incrementCycles();

            int op = program[this.state.getPc()];
            int operand1 = program[(this.state.getPc() + 1) % 16];
            int operand2 = program[(this.state.getPc() + 2) % 16];

            switch (op) {
                case 0xB1:
                    this.state.setPc(operand1);
                    break;
                case 0xB2:
                    if (this.state.getRegisters()[operand1] == state.getAcc()) {
                        state.setPc(operand2);
                    } else {
                        state.incrementPcBy(3);
                    }
                    break;
                case 0xB3:
                    if (this.state.getAcc() == operand1) {
                        this.state.setPc(operand2);
                    } else {
                        state.incrementPcBy(3);
                    }
                    break;
                case 0xC0:
                    this.state.addToAcc(this.state.getRegisters()[operand1]);
                    state.incrementPcBy(2);
                    break;
                case 0xC1:
                    this.state.addToAcc(operand1);
                    this.state.incrementPcBy(2);
                    break;
                case 0xC2:
                    this.state.incrementInc();
                    this.state.incrementPc();
                    break;
                case 0xC3:
                    this.state.decrementInc();
                    this.state.incrementPc();
                    break;
                case 0xC4:
                    this.state.setInc(0);
                    this.state.incrementPc();
                    break;
                case 0xC5:
                    this.state.setAcc(this.state.getInc());
                    this.state.incrementPc();
                    break;
                case 0xC6:
                    this.state.setInc(this.state.getAcc());
                    this.state.incrementPc();
                    break;
                case 0xD0:
                    this.state.setAcc(this.state.getRegisters()[operand1]);
                    this.state.incrementPcBy(2);
                    break;
                case 0xD1:
                    this.state.setAcc(operand1);
                    this.state.incrementPcBy(2);
                    break;
                case 0xD2:
                    this.state.getRegisters()[operand1] = this.state.getAcc();
                    this.state.incrementPcBy(2);
                    break;
                case 0x00:
                    return;
                default:
                    throw new UnknownOpCodeException("Op code '" + Integer.toHexString(op) + "' at address 0x" + Integer.toHexString(this.state.getPc()) + "'");
            }
        }
    }
}
