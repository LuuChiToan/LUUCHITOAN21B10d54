**QUẢN LÝ CHI TIÊU CÁ NHÂN**

-Đầu tiên đăng nhập vào phần mêm quản lí 
Tài khoản: admin
Mật khẩu: admin
- Sau khi đăng nhập vào giao diện sẽ bao gồm Trang chủ, Quản lí thu chi, Thống kê.
* Trang chủ sẽ là giao diện chính
* Giao diện Quản lí thu chi bao gồm một bảng gồm 5 cột là ID, Description, Amount, Type
+ Date được lấy theo kiểu JDateChooser nên ngày đầu tiên của tuần bắt đầu bằng ngày chủ nhật 
+ Giao diện Quản lí thu chi bao gồm một combobox gồm 4 mục Date, Description, Month, Week phục vụ cho thanh tìm kiếm tương ứng
+ Tìm kiếm theo ngày sẽ phải tìm kiếm theo đúng định dạng dd/mm/yyyy 
+ Tìm kiếm theo tháng sẽ tìm kiếm theo dạng mm 
+ Tìm kiếm theo tuần thì sẽ tìm kiếm 1 ngày theo định dạng dd/mm/yyyy rồi nó sẽ truy ra tất cả các ngày thuộc tuần đó
+ Tìm kiếm theo Description để lọc ra những hoạt động giống nhau mà mình muốn thống kê  
+ Nút Add để thêm dữ liệu vào bảng
+ Nút Edit để sửa lại dữ liệu khi muốn sửa một hàng nào đó 
+ Nút Delete sẽ xoá một hàng mà mình chọn
+ Nút Clear sẽ dùng để xoá hết dữ liệu khi mình đang nhập và để trống các texfield mà mình điền ID, Date, Description, Amount, Type 
 + Bên cạnh bảng table sẽ hiện Total Expense, Total Income, Profit( hiển thị khi Total Expense < Total Income), Loss ( hiển thị khi Total Expense lớn hơn Total Income)
+ Khi tìm kiếm nếu danh sách sau khi tìm kiếm có Total Expense >0 sẽ hiện ra cảnh báo " Expense exceeds Income"  
* Giao diện Thống kê sẽ gồm 1 bảng table từ giao diện Quản lí thu chi để đối chiếu số liệu dễ dàng hơn
 Phần dưới bảng table sẽ là biểu đồ tròn để thống kê Tổng thu và Tổng chi sau khi tìm kiếm Date, Month, Week, Description mà mình muốn tìm 
* Phần đăng xuất sẽ là 1 nút để đăng xuất ra màn hình đăng nhập

Link fileGihub:https://github.com/LuuChiToan/LuuChiToanB10D54