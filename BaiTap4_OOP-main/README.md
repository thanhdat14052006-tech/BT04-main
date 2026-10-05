# W4 – Hệ Thống Tính Lương và Thưởng Nhân Sự

## Mô tả

Bài tập tuần 4 môn Lập trình Hướng đối tượng xây dựng hệ thống tính thu nhập hằng tháng cho ba loại nhân sự trong doanh nghiệp. Hệ thống áp dụng các khái niệm: kế thừa, nạp chồng (overloading) và ghi đè (overriding) phương thức.

---

## Cấu trúc dự án

```
W4/
├── src/                    # Mã nguồn
│   ├── Employee.java           # Lớp cơ sở (abstract)
│   ├── SalariedEmployee.java   # Nhân viên lương cố định
│   ├── HourlyEmployee.java     # Nhân viên theo giờ
│   ├── SalesEmployee.java      # Nhân viên kinh doanh
│   └── Payroll.java            # Quản lý bảng lương
└── README.md
```

---

## Các lớp chính

| Lớp | Vai trò |
|-----|---------|
| `Employee` *(abstract)* | Lớp cơ sở chứa thông tin chung: mã, tên, phòng ban, thưởng |
| `SalariedEmployee` | Lương cố định + phụ cấp trách nhiệm |
| `HourlyEmployee` | Lương theo giờ, tính overtime khi > 160h/tháng |
| `SalesEmployee` | Lương cơ bản + hoa hồng theo doanh số |
| `Payroll` | Quản lý danh sách nhân sự, tổng hợp & tìm kiếm |

---

## Công thức tính lương

```
SalariedEmployee : grossPay = monthlySalary + responsibilityAllowance + monthlyBonus

HourlyEmployee   : basePay  = workedHours × hourlyRate                  (≤ 160h)
                            = 160×rate + (hours−160)×rate×1.5           (> 160h)
                   grossPay = basePay + monthlyBonus

SalesEmployee    : grossPay = baseSalary + salesRevenue×commissionRate + monthlyBonus
```

---

## Điểm OOP nổi bật

- **Kế thừa**: `SalariedEmployee`, `HourlyEmployee`, `SalesEmployee` đều kế thừa `Employee`.
- **Overloading**: `addBonus()` có 3 chữ ký khác nhau → chọn tại **compile-time**.
- **Overriding**: `calculateGrossPay()` được ghi đè ở mỗi lớp con → chọn tại **runtime** (đa hình).
- **Encapsulation**: Mọi thuộc tính đều `private`, truy cập qua getter/setter có kiểm soát.
- **Đa hình**: `Payroll` gọi `calculateGrossPay()` qua kiểu `Employee`, không dùng `if/else` theo loại.

---

## Hướng dẫn chạy chương trình

**Yêu cầu:** JDK 11 trở lên (`java --version` để kiểm tra).

**Bước 1** – Mở terminal, di chuyển đến thư mục:
```bash
cd "FILE_PATH"
```

**Bước 2** – Biên dịch toàn bộ source:
```bash
javac -d out src/*.java
```
> Lệnh này compile tất cả file `.java` trong `src/` và xuất `.class` vào thư mục `out/`.

**Bước 3** – Chạy chương trình:
```bash
java -cp out Main
```

**Tóm tắt (chạy nhanh 1 dòng):**
```bash
javac -d out src/*.java && java -cp out Main
```

> **Lưu ý:** Nếu tiếng Việt hiển thị lỗi font trên PowerShell, chạy `chcp 65001` trước để chuyển sang encoding UTF-8.
