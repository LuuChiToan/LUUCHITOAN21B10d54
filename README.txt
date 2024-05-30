**QUẢN LÝ CHI TIÊU CÁ NHÂN**
Link file bài làm trên github: https://github.com/LuuChiToan/LuuChiToanB10D54

HƯỚNG DẪN SỬ DỤNG PHẦN MỀM QUẢN LÍ THU CHI CÁ NHÂN 

-Đầu tiên đăng nhập vào phần mêm quản lí 
Tài khoản: admin
Mật khẩu: admin
- Sau khi đăng nhập vào giao diện sẽ bao gồm Trang chủ, Quản lí thu chi, Thống kê.
1. Trang chủ sẽ là giao diện chính nói về bài học trong cuộc sống về việc quản lí thu chi sao cho đúng đắn

2. Giao diện Quản lí thu chi bao gồm 2 bảng, mỗi bảng  gồm 5 cột là ID, Description, Amount, Type. Khi chọn Type là Expense thì sẽ đưa dữ liệu vào bảng 1, khi chọn Type là Income sẽ đưa dữ liệu vào bảng 2.

- Date được lấy theo kiểu JDateChooser nên ngày đầu tiên của tuần bắt đầu bằng ngày chủ nhật 
  Giao diện Quản lí thu chi sẽ gồm các chức năng sau:

- Giao diện Quản lí thu chi bao gồm một combobox gồm 5 lựa chọn là Date, Description, Month, Week, Amount Range phục vụ cho thanh tìm kiếm tương ứng.
- Nút Search 
+ Tìm kiếm theo Date sẽ phải tìm kiếm theo đúng định dạng dd/mm/yyyy, ví dụ 20/01/2024

+ Tìm kiếm theo Month sẽ tìm kiếm theo dạng mm, ví dụ tìm kiếm tháng 1 sẽ là 01

+ Tìm kiếm theo Week thì sẽ tìm kiếm 1 ngày theo định dạng dd/mm/yyyy, ví dụ tìm kiếm  20/01/2024 sẽ truy ra tất cả các ngày thuộc tuần mà chứa 20/01/2024

+ Tìm kiếm theo Description để lọc ra những hoạt động giống nhau mà mình muốn thống kê 

+ Tìm kiếm theo Amount Range sẽ tìm kiếm theo khoảng tiền mà mình muốn tìm, tìm kiếm Amount Range sẽ tìm kiếm theo dạng "Amount1 to Amount2", ví dụ tìm kiếm khoảng tiền từ 1000 đến 5000 sẽ tìm kiếm như sau: 1000 to 5000 

- Nút Cancel để xoá những String mà mới tìm kiếm và đưa 2 bảng về dạng ban đầu.

+ Nút Add để thêm dữ liệu vào bảng

+ Nút Edit để sửa lại dữ liệu khi muốn sửa một hàng nào đó 

+ Nút Delete sẽ xoá một hàng mà mình chọn để xoá

+ Nút Clear sẽ dùng để xoá hết dữ liệu khi mình đang nhập và để trống các texfield mà mình điền ID, Date, Description, Amount, Type 

 - Bên cạnh 2 bảng sẽ hiện Total Expense, Total Income, Profit( hiển thị khi Total Expense < Total Income), Loss ( hiển thị khi Total Expense > Total Income)

+ Khi tìm kiếm nếu danh sách sau khi tìm kiếm có Total Expense > 0 sẽ hiện ra cảnh báo " Expense exceeds Income"  

- Bên cạnh bảng có 2 nút Sort by Date và Sort by Amount

+ Nút Sort by Date để sắp xếp ngày theo thứ tự từ trước tới sau 

+ Nút Sort by Amount để sắp xếp khoản tiền theo thứ tự từ bé đến lớn.

+ Sau khi Search vẫn có thể Sort by Date và Sort by Amount

3. Giao diện Thống kê sẽ gồm 2 bảng giống 2 bảng từ giao diện Quản lí thu chi để đối chiếu số liệu dễ dàng hơn khi thống kê.
 - Phải tìm kiếm trong giao diện Quản lí thu chi thì mới thống kê được Total Income và Total Expense.

 - Biểu đồ thống kê là biểu đồ % dạng biểu đồ tròn để thống kê Total Income và Total Expense.

 - Bên phải biểu đồ sẽ hiện Total Expense, Total Income, Profit( hiển thị khi Total Expense < Total Income), Loss ( hiển thị khi Total Expense > Total Income)

 -Phần dưới bảng table sẽ là biểu đồ tròn để thống kê  Total Income và Total Expense  sau khi tìm kiếm Date, Month, Week, Description, Amount Range mà mình muốn tìm 

4. Phần đăng xuất để đăng xuất ra màn hình đăng nhập

Link fileGihub:https://github.com/LuuChiToan/LuuChiToanB10D54