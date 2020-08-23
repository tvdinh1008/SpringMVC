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
