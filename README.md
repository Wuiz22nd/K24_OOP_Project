# 🧋 Bubble Tea POS - Java OOP Project

## 📌 Giới thiệu

Đây là project xây dựng hệ thống **Order Trà Sữa (Bubble Tea POS)** bằng Java, áp dụng các nguyên lý **Lập trình hướng đối tượng (OOP)** và **Design Pattern (Decorator)**.

Ứng dụng cho phép:

* Tạo đơn hàng trà sữa
* Thêm topping linh hoạt
* Tính tiền tự động
* Quản lý kho nguyên liệu

---

## 🎯 Mục tiêu

* Áp dụng OOP (Class, Interface, Inheritance, Polymorphism)
* Sử dụng **Decorator Pattern** để thêm topping động
* Quản lý dữ liệu thực tế (file txt/csv)
* Xử lý lỗi và ngoại lệ trong hệ thống

---

## 🚀 Chức năng chính

### ✅ MUST

* Quản lý menu với các loại trà sữa:

  * Truyền thống
  * Matcha
  * Socola

* Thêm topping bằng Decorator Pattern:

  * Trân châu
  * Thạch
  * Kem cheese
  * Đường
  * Đá

* Tạo đơn hàng:

  * Nhiều ly
  * Tính giá từng ly và tổng tiền
  * In hóa đơn chi tiết

* Quản lý kho:

  * Lưu/đọc file (txt/csv)
  * Trừ nguyên liệu tự động
  * Kiểm tra tồn kho trước khi order

* Xử lý lỗi:

  * Không cho order nếu thiếu nguyên liệu

---

## 🧠 Thiết kế OOP

### 🔹 Các class chính

#### `Tea` (abstract)

* Component cơ bản cho trà sữa
* Thuộc tính:

  * `name`
  * `basePrice`
* Method:

  * `cost()`
  * `getDescription()`
  * `deductInventory()`

#### `MilkTea`

* Kế thừa `Tea`
* Đại diện các loại trà sữa cơ bản

---

#### `ToppingDecorator` (abstract)

* Decorator cho topping
* Chứa:

  * `Tea wrappedTea`
* Override:

  * `cost()`
  * `getDescription()`
  * `deductInventory()`

---

#### `Order`

* Quản lý đơn hàng
* Thuộc tính:

  * `List<Tea> items`
* Method:

  * `addDrink()`
  * `calculateTotal()`
  * `printInvoice()`

---

#### `Inventory`

* Quản lý kho nguyên liệu
* Thuộc tính:

  * `Map stock`
  * `FILE_PATH`
* Method:

  * `loadFromFile()`
  * `saveToFile()`
  * `checkAndDeduct()`
  * `getInstance()` (Singleton)

---

## 🔗 Quan hệ giữa các class

* `Order` **has-a** nhiều `Tea`
* `ToppingDecorator` **is-a** `Tea` và **has-a** `Tea`
* `Inventory` sử dụng **Singleton Pattern**

---

## 🔄 Luồng hoạt động (Happy Path)

1. Hiển thị menu → chọn trà sữa
2. Chọn topping → áp dụng Decorator
3. Kiểm tra kho → trừ nguyên liệu
4. Tính tiền → thêm vào đơn
5. In hóa đơn

---

## ⚠️ Xử lý lỗi

* Kiểm tra input hợp lệ
* Exception:

  * `InsufficientStockException`
* Không cho order nếu thiếu nguyên liệu

---

## 🛠️ Công nghệ sử dụng

* Java
* OOP
* Decorator Pattern
* File I/O (txt/csv)
* Maven / Gradle

---

## ▶️ Cách chạy project

### 🔧 Maven

```bash
mvn clean test
mvn exec:java
```

### 🔧 Gradle

```bash
gradle test
gradle run
```

---

## 📂 Cấu trúc project (gợi ý)

```
src/
├── model/
│   ├── Tea.java
│   ├── MilkTea.java
│   ├── ToppingDecorator.java
├── order/
│   ├── Order.java
├── inventory/
│   ├── Inventory.java
├── Main.java
```

---

## 👨‍💻 Nhóm thực hiện

* SV1: Quí
* SV2: Phát
* SV3: Hoa

---

## 🔗 Git Repository

👉 https://github.com/Wuiz22nd/K24_OOP_Project.git

---

## 📌 Ghi chú

* Project phục vụ mục đích học tập OOP
* Có thể mở rộng thêm UI hoặc database trong tương lai

---
