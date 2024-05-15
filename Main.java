// Padrão Singleton
class Database {
    private static Database instance;
    
    private Database() {}
    
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    // Métodos para interagir com o banco de dados...
}

// Padrão Factory
interface Employee {
    void doWork();
}

class Developer implements Employee {
    @Override
    public void doWork() {
        System.out.println("Desenvolvendo código...");
    }
}

class Manager implements Employee {
    @Override
    public void doWork() {
        System.out.println("Gerenciando equipe...");
    }
}

class EmployeeFactory {
    public static Employee createEmployee(String type) {
        if (type.equalsIgnoreCase("Developer")) {
            return new Developer();
        } else if (type.equalsIgnoreCase("Manager")) {
            return new Manager();
        }
        return null;
    }
}

// Padrão Builder
class EmployeeBuilder {
    private String name;
    private int age;
    private String position;
    
    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }
    
    public EmployeeBuilder setAge(int age) {
        this.age = age;
        return this;
    }
    
    public EmployeeBuilder setPosition(String position) {
        this.position = position;
        return this;
    }
    
    public Employee build() {
        // Realiza validações...
        return EmployeeFactory.createEmployee(position);
    }
}

// Padrão Observer
interface Observer {
    void update(String message);
}

class HRDepartment implements Observer {
    @Override
    public void update(String message) {
        System.out.println("HR recebeu a seguinte mensagem: " + message);
    }
}

class PayrollDepartment implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Folha de pagamento recebeu a seguinte mensagem: " + message);
    }
}

// Padrão Strategy
interface Task {
    void performTask();
}

class CodingTask implements Task {
    @Override
    public void performTask() {
        System.out.println("Realizando tarefa de codificação...");
    }
}

class ManagementTask implements Task {
    @Override
    public void performTask() {
        System.out.println("Realizando tarefa de gestão...");
    }
}

// Padrão Decorator
abstract class EmployeeDecorator implements Employee {
    protected Employee employee;
    
    public EmployeeDecorator(Employee employee) {
        this.employee = employee;
    }
    
    @Override
    public void doWork() {
        employee.doWork();
    }
}

class SeniorDeveloper extends EmployeeDecorator {
    public SeniorDeveloper(Employee employee) {
        super(employee);
    }
    
    @Override
    public void doWork() {
        super.doWork();
        System.out.println("Mentorando outros desenvolvedores...");
    }
}

// Padrão Adapter
class HRSystem {
    public void addEmployee(String name, int age, String position) {
        System.out.println("Adicionando funcionário ao sistema de RH...");
    }
}

class HRAdapter implements Employee {
    private HRSystem hrSystem;
    
    public HRAdapter(HRSystem hrSystem) {
        this.hrSystem = hrSystem;
    }
    
    @Override
    public void doWork() {
        hrSystem.addEmployee("João", 30, "Manager");
    }
}

public class Main {
    public static void main(String[] args) {
        // Singleton
        Database database = Database.getInstance();
        
        // Factory
        Employee developer = EmployeeFactory.createEmployee("Developer");
        developer.doWork();
        
        // Builder
        Employee newEmployee = new EmployeeBuilder()
                                    .setName("Maria")
                                    .setAge(25)
                                    .setPosition("Manager")
                                    .build();
        newEmployee.doWork();
        
        // Observer
        Observer hr = new HRDepartment();
        Observer payroll = new PayrollDepartment();
        
        // Strategy
        Task codingTask = new CodingTask();
        codingTask.performTask();
        
        Task managementTask = new ManagementTask();
        managementTask.performTask();
        
        // Decorator
        Employee seniorDeveloper = new SeniorDeveloper(new Developer());
        seniorDeveloper.doWork();
        
        // Adapter
        HRSystem hrSystem = new HRSystem();
        Employee hrAdapter = new HRAdapter(hrSystem);
        hrAdapter.doWork();
    }
}
