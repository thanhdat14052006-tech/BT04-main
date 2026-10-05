/**
Nguyễn Thành Đạt
202419040
 */
import java.util.ArrayList;
import java.util.List;

public class Payroll {

    private String period;                    // Kỳ lương, ví dụ "2026-09"
    private List<Employee> employees;

    // ── Constructor ──────────────────────────────────────────────────────────

    public Payroll(String period) {
        if (period == null || period.isBlank())
            throw new IllegalArgumentException("Kỳ lương không được rỗng.");
        this.period    = period.trim();
        this.employees = new ArrayList<>();
    }

    // ── Thêm nhân sự ─────────────────────────────────────────────────────────

    /**
     * Thêm nhân sự vào bảng lương.
     * @return true nếu thêm thành công, false nếu trùng mã.
     */
    public boolean addEmployee(Employee employee) {
        if (employee == null) return false;
        if (findEmployee(employee.getEmployeeId()) != null) {
            System.out.printf("⚠ Nhân sự [%s] đã tồn tại trong bảng lương %s.%n",
                    employee.getEmployeeId(), period);
            return false;
        }
        employees.add(employee);
        return true;
    }

    // ── Tìm kiếm ─────────────────────────────────────────────────────────────

    /**
     * Tìm nhân sự theo mã.
     * @return Employee hoặc null nếu không tìm thấy.
     */
    public Employee findEmployee(String employeeId) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(employeeId)) return e;
        }
        return null;
    }

    // ── Tổng hợp ─────────────────────────────────────────────────────────────

    /** Tính tổng bảng lương (gọi calculateGrossPay() qua đa hình). */
    public double calculateTotalPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateGrossPay(); // runtime polymorphism
        }
        return total;
    }

    /** Tính tổng lương theo phòng ban. */
    public double calculatePayrollByDepartment(String department) {
        double total = 0;
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                total += e.calculateGrossPay();
            }
        }
        return total;
    }

    /** Tìm nhân sự có thu nhập cao nhất. */
    public Employee findHighestPaidEmployee() {
        if (employees.isEmpty()) return null;
        Employee highest = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateGrossPay() > highest.calculateGrossPay()) {
                highest = e;
            }
        }
        return highest;
    }

    // ── Hiển thị ─────────────────────────────────────────────────────────────

    /** Hiển thị toàn bộ bảng lương (gọi displayPayrollInfo() qua đa hình). */
    public void displayPayroll() {
        System.out.println("═════════════════════════════════════════════");
        System.out.printf("     BẢNG LƯƠNG KỲ %s  (%d nhân sự)%n",
                period, employees.size());
        System.out.println("═════════════════════════════════════════════");

        if (employees.isEmpty()) {
            System.out.println("  (Danh sách nhân sự trống)");
        } else {
            for (Employee e : employees) {
                e.displayPayrollInfo(); // runtime polymorphism
            }
            System.out.println("─────────────────────────────────────────────");
            System.out.printf("%-20s : %,.0f đ%n", "TỔNG BẢNG LƯƠNG", calculateTotalPayroll());

            Employee top = findHighestPaidEmployee();
            if (top != null) {
                System.out.printf("%-20s : %s (%,.0f đ)%n",
                        "Thu nhập cao nhất", top.getFullName(), top.calculateGrossPay());
            }
        }
        System.out.println("═════════════════════════════════════════════");
    }

    // ── Getter ───────────────────────────────────────────────────────────────

    public String getPeriod()          { return period; }
    public int    getEmployeeCount()   { return employees.size(); }
}
