# SpringMVC

+ Có sự khác nhau giữa $().attr() và $().prop(): ví dụ "input type="checkbox" id="test" checked="checked" " thì $().attr("checked")=checked (giá trị của checked) còn $().prop("checked")=true (sự tồn tại của checked. nếu input không có checked="checked" thì $().attr("checked")=undefine và $().prop("checked")=false. TH muốn set giá trị thì $().prop("checked",true). attr bắt buộc phải có trong thẻ thì mới lấy dc giá trị


+ Sử dụng ajax(JSON) khi click thêm sửa xóa,..(crud) tức sử dụng api. Còn controller để đổ dữ liệu ra hoặc trả về trang jsp
	- Do api nó cũng có chức năng gần tương tự như controller tức nó gần như cùng tầng với controller. vì thế để hiểu đc các @ thì nó cần cấu hình trong file dispather-servlet.xml: context:component-scan

+ Sử dụng sping Form
	- giá trị path="" trong jsp sẽ tương ứng trong controller.
	- form:options và form:checkboxes sẽ sử dụng path làm default value 
	
+ Quy tắc phân tầng:
	- entity sẽ làm việc với DAO
	- DTO sẽ làm việc với sevice( do đó cần convert entity->DTO và ngược lại)
	- Model sẽ chứa DTO và các thuộc tính liên quan và làm việc với controller
	
+ Spring mvc interceptor by implementing HandlerInterceptor. 
	- Cấu hình 2 file: java implements HandlerInterceptor và file dispatcher-servlet.xml (<mvc:interceptors> ...</mvc:interceptors>)
	- Sử dụng khi muốn xử lý cái gì đó trước khi url đc request hoặc khi request hoặc sau khi request,..
	- HandlerInterceptor interface defined 3 methods.
	- preHandle(request, response, handler) – Used to intercept the request before handed over to the handler method. Here handler is the chosen handler object to handle the request.
	- postHandle(request, response, handler, modelAndView) – Used to intercept the request after handler has completed request processing but DispatcherServlet is yet to render the view.
	- afterCompletion(request, response, handler, exception)
	- It is called once the handler execution is complete and view is rendered as well.
	
+ FetchType mặc định JPA:

  - OneToMany: LAZY
	- ManyToOne: EAGER
	- ManyToMany: LAZY
	- OneToOne: EAGER
  
  - "FetchType.LAZY" = This does not load the relationships unless you invoke it via the getter method. "FetchType.EAGER" = This loads all the relationships.
  -> Để lấy dữ liệu trong lazy khi truy vấn ta cố tình lấy getter().<thuộc tính> hay trong list thì lấy get(0) của thuộc tính đó nó sẽ loading data 
  ví dụ: CustomerEnity có quan hệ n-n với RoleEntity ( qua bảng customer_role): 
   
    @ManyToMany(fetch = FetchType.LAZY)
    
    @JoinTable(name="customer_role", joinColumns = @JoinColumn(name = "userid",nullable = false,updatable = false),
    inverseJoinColumns = @JoinColumn(name = "roleid",nullable = false,updatable = false))
    
    private List<RoleEntity> roles = new ArrayList<>();
  
  - Để lấy roles thì:
  
   entityManager.getTransaction().begin();
  
    try {
    
			StringBuilder sql=new StringBuilder("");
			sql.append("Select * from customer Where username=:username and status=:status");
			
			Query q=entityManager.createNativeQuery(sql.toString(),CustomerEntity.class);
			q.setParameter("username", username);
			q.setParameter("status", status);
			result=(CustomerEntity)q.getSingleResult();
			//result.getRoles().get(0); //cố tình lấy thì nó sẽ tạo truy vấn
			result.getRoles().size();
			entityManager.getTransaction().commit();
		} 

  - Khi show hibernate: nó sẽ tạo ra 2 câu query. lấy đc là  vì nó vẫn nằm trong transaction nên vẫn lấy được
  
  - Nếu sử dụng FetchType.EAGER thì nó chỉ 1 câu query nhưng nó JOIN các bảng lại
  
  - Tài liệu cách tương tự: https://stackjava.com/hibernate/code-vi-du-hibernate-fetchtype-lazy-lazy-loading.html 

+ Có điểm khác nhau giữa Spring data jpa và hibernate jpa2.1
  Nếu muốn sử dụng auditing(@LastModifiedDate,@CreatedDate,@CreatedBy,@LastModifiedBy) của spring data jpa thôi thì ta chỉ cần cấu hình 3 file:
  - JPAConfig.java
  
      @Configuration
      public class JPAConfig {
      
        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
          LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
          em.setPersistenceUnitName("persistence-data");
          return em;
         }
      }
      
  - JpaAuditingConfig.java
  
      @Configuration
      @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
      
      public class JpaAuditingConfig {
    
        @Bean
        public AuditorAware<String> auditorProvider() {
          return new AuditorAwareImpl();
        }
        public static class AuditorAwareImpl implements AuditorAware<String> {
          @Override
          public String getCurrentAuditor() {
            String name = "SYSTEM";
            //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //if (authentication == null) {
            //	return name;
            //}
            // authentication.getName();
            return name;
           }
         }
        }
   - Và persistence.xml
   
      <?xml version="1.0" encoding="UTF-8"?>
      <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                   http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd" version="2.1">
                   
               <persistence-unit name="persistence-data" transaction-type="RESOURCE_LOCAL">	
            <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
            <!--hibernate-jpa-2.1-api: This is how Hibernate is hooked into our application to be used as JPA implementation -->
                <class>com.tvdinh.entity.CustomerEntity</class>
                <class>com.tvdinh.entity.RoleEntity</class>
                <properties>
                <!-- Property JDBC -->
                <property name="javax.persistence.jdbc.driver" value="com.microsoft.sqlserver.jdbc.SQLServerDriver" />
                <property name="javax.persistence.jdbc.url" value="jdbc:sqlserver://localhost:1433;databaseName=HibernateDemo;integratedSecurity=true" />
                <property name="javax.persistence.jdbc.user" value="dinh1" />
                <property name="javax.persistence.jdbc.password" value="dinh1" />
                <!-- config hibernate -->
                <!-- <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" /> -->
                <property name="hibernate.dialect" value="org.hibernate.dialect.SQLServerDialect"/>
                    <property name="hibernate.hbm2ddl.auto" value="update" />
                <!-- <property name="hibernate.hbm2ddl.auto" value="create-drop"/> -->
                <property name="hibernate.show_sql" value="true" />
                <property name="hibernate.format_sql" value="true" />
                 <property name="hibernate.temp.use_jdbc_metadata_defaults" value="false"/>
              </properties>
               </persistence-unit>
      </persistence> 



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
