# SpringMVC

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
            <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider> <!--hibernate-jpa-2.1-api: This is how Hibernate is hooked into our application to be used as JPA implementation -->
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
