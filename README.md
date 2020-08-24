# SpringMVC



+ Database->(Dao-interface chứa phương thức)->entity(chuyển qua lại với nhau qua package Utils(class beanUtil))->DTO->(Serice - interface chứa phương thức)

+ DTO(Data Transfer Object)
  - private property
  - getter and setter
  - extents
  - implements( vidu Serializable)
  
 + Pojo(Plain Old Java Object): là java bean thuần
  - private property
  - getter and setter
  - Không được extends, implements từ các khác trừ Serializable
  
+ Model có thuộc tính là pojo và pojo=new DTO: =>model chứa DTO vì thế ở view để mapp đc dữ liệu thì input sẽ đặt name="pojo.<tên của thuộc tính dto>" 
  - chú ý tất cả các trường ở tầng view sẽ đổ vào model.(chú ý nếu có tạo new như ở trên thì để mapp đc thì thêm tên.xxx - bình thường thì chỉ cần <tên thuộc tính> là đc)
  - DTO để mapp với entity
