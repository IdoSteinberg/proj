class Main {
    public static void main(String[] args) {
        Motor motor1 = new Motor(0.6, 2, "Kraken");
        Motor motor2 = new Motor(0.4, 3, "Falcon");
        Motor motor3 = new Motor(0.2, 6, "Falcon2");
        Motor motor4 = new Motor(0.3, 0, "Falcon4");
        //druker is here :)
        motor2.printPort();
        motor1.changePower(0.7);
        motor1.printPower();
        motor1.printType();
        System.out.println(motor1.getStats());
        System.out.println(motor2.isStronger(motor1));
        Motor[] motors = {motor1, motor2, motor3};
        Robot robot = new Robot(4);
        System.out.println(robot.addMotor(motor1));
        System.out.println(robot.addMotor(motor2));
        System.out.println(robot.addMotor(motor3));
        System.out.println(robot.addMotor(motor4));
        robot.enable();
        robot.disable();
        robot.enable();
        Intake intaker = new Intake("areataker", motors);
        intaker.setName("notArea51taker");
        System.out.println(intaker.getName());
        intaker.enable();
        intaker.disable();
        intaker.switchState(intaker.ENABLED);
        intaker.switchState(intaker.DISABLED);
        System.out.println(intaker.getSubsystemInfo());
        RobotBattery robotA = new RobotBattery(5.0);
        LoggedSubsystem[] arr = {intaker, robotA};
        printStatus(arr);
    }
    public static void printStatus(LoggedSubsystem[] subsystems) {
        for (LoggedSubsystem subsystem : subsystems) {  
            System.out.println(subsystem.getSubsystemInfo());
        }
    }

}


class Motor {
    private double power;
    private int port;
    private String type;
   
    public Motor(double power, int port, String type) {
        if(power<1&&power>-1) this.power = power;
        else throw new IllegalArgumentException("power must be between -1 and 1");
        if (port < 0) {
            throw new IllegalArgumentException("Port cannot be negative.");
        }
        this.port = port;
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty.");
        }
        this.type = type;
    }
    public void printPort() {System.out.println(this.port);}
    public void printPower() {System.out.println(this.power);}
    public void printType() {System.out.println(this.type);}
    public double getPower() {return(this.power);}
    public void changePower(double num) {
        if(num<1&&num>-1) this.power = num;
        else throw new IllegalArgumentException("power must be between -1 and 1");
    }
    public String getStats() {
        return ("power: " + this.power + ", port: " + this.port + ", type: " + this.type);
    }
    public boolean isStronger(Motor motor) {
        if (motor == null) {
            throw new IllegalArgumentException("Cannot compare strength against a null Motor.");
        }
        return Math.abs(this.power) > Math.abs(motor.getPower());
    }
    public int getPort() {return this.port;}
    public String getType() {return this.type;}
    public void resetMotor() {this.power = 0;}
}


class Robot {
    private int connections;
    private Motor[] motors;
    public Robot(int connections) {
        this.connections = connections;
        this.motors = new Motor[connections];
    }
    public Robot(int connections, Motor[] newMotors) {
        this.connections = connections;
        this.motors = new Motor[connections];
        if (newMotors != null) {
            for (Motor motor : newMotors) {
                if (motor != null) {
                    addMotor(motor);
                }
            }
        }
    }
    public boolean addMotor(Motor motor1) {
        if (motor1 == null) return false;
        if (motor1.getPort() < 0 || motor1.getPort() >= this.connections) {
            return false;
        }
        if (this.motors[motor1.getPort()] == null) {
            this.motors[motor1.getPort()] = motor1;
            return true;
        }
        return false;
    }
    public void disable() {
        for(Motor motor: this.motors) {
            if(motor != null) motor.resetMotor();
        }
    }
    public void enable() {
        System.out.println("information: ");
        for(Motor motor: this.motors) {
            if(motor != null){
                motor.getStats();
            }
            else System.out.println("No motor connected to port");
        }
    }
}
abstract class SubsystemBase {
    private String name;
    private Motor[] motors_;
    public SubsystemState state;
    public SubsystemState ENABLED = SubsystemState.ENABLED;
    public SubsystemState DISABLED = SubsystemState.DISABLED;
    enum SubsystemState {
        ENABLED, DISABLED
    }
    public SubsystemBase(String name, Motor[] motors_) {
        this.name = name;
        this.motors_ = motors_;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setMotors(Motor[] motors_) {
        this.motors_ = motors_;
    }
    //getters
    public double getTotalPowerDraw() {
        double total = 0.0;
        if (getMotors() != null) {
            for (Motor motor : getMotors()) {
                if (motor != null) {
                    total += Math.abs(motor.getPower());
                }
            }
        }
        return total;
    }
    public String getName() {
        return(this.name);
    }
    public Motor[] getMotors() {
        return(this.motors_);
    }
    public SubsystemState getState() {
        return this.state;
    }
    abstract void enable();
    abstract void disable();
    abstract void switchState(SubsystemState state);
}
class Intake extends SubsystemBase implements LoggedSubsystem{
    public Intake(String name, Motor[] motors_) {
        super(name, motors_);
    }
    @Override
    public void enable(){
        System.out.println("Intaker opened");
        state = ENABLED;
    }
    @Override
    public void disable(){
        System.out.println("Intaker closed");
        state = DISABLED;
    }
    @Override
    public void switchState(SubsystemState state){
        switch (state) {
            case ENABLED:
                this.enable();
                this.state = ENABLED;
                break;
            case DISABLED:
                this.disable();
                this.state = DISABLED;
                break;
            default:
                throw new IllegalArgumentException("State cannot be null or empty.");
        }
    }
    @Override
    public String getSubsystemInfo(){
        return ("Name: " + getName() + ", state: " + getState() + ", total power used: " + getTotalPowerDraw());
    }
}


interface LoggedSubsystem{
    public String getSubsystemInfo();
}


interface RobotSubsystem{
    //private String name; לא ניתן לקלוט
    //private Motor[] motors_;
    //public SubsystemState state;
    public SubsystemState ENABLED = SubsystemState.ENABLED;
    public SubsystemState DISABLED = SubsystemState.DISABLED;
    enum SubsystemState {
        ENABLED, DISABLED
    }
    //setters רק מוצהרים
    void setName(String name);
    void setMotors(Motor[] motors_);
    //getters רק מוצהרים
    String getName();
    Motor[] getMotors();
    public SubsystemState getState();
    public void enable();
    public void disable();
    public void switchState(SubsystemState state);
}
//לא ממש הצלחתי, הבעיה העיקרית הייתה שהמשתנים בממשקים חייבים להיות קבועים ככה שלא ניתן להפעיל setterים או getterים וגם בממשקים אין קונסטרקטורים אז אי אפשר לקלוט משתנים.


class RobotBattery implements LoggedSubsystem{
    private double voltage;
    public RobotBattery(double voltage){
        this.voltage = voltage;
    }
    public void setVoltage(double volt){
        this.voltage = volt;
    }
    public double getVoltage(){
        return(this.voltage);
    }
    public String getSubsystemInfo(){
        return("Current voltage: " + getVoltage());
    }
}
