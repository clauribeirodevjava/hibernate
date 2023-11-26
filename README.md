# hibernate
# dependências:
```
<dependencies>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.200</version>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.6.0.Final</version>
		</dependency>
	</dependencies>
```

# configuração:

```
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>

	<!-- Configurações de conexão com o banco de dados H2 -->
	<session-factory>
		<property name="hibernate.connection.driver_class">org.h2.Driver</property>
		<property name="hibernate.connection.url">jdbc:h2:file:./data/testdb</property>
		<property name="hibernate.connection.username">sa</property>
		<property name="hibernate.connection.password"></property>
		<!-- Especifica o dialeto do banco de dados -->
		<property name="hibernate.dialect">org.hibernate.dialect.H2Dialect</property>
		<!-- Mostra todo SQL executado no console -->
		<property name="hibernate.show_sql">true</property>
		<!-- Atualiza automaticamente o esquema do banco de dados -->
		<property name="hibernate.hbm2ddl.auto">update</property>
		<!-- Menciona a classe de entidade -->
		<mapping class="com.fiap.hibernate.Product" />
	</session-factory>

</hibernate-configuration>
```

# entidade mapeamento
```
package com.fiap.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
```
# Teste inclusão e listagem:
```
package com.fiap.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
	public static void main(String[] args) {
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
			Product product = new Product();
			product.setName("Produto Teste");
			product.setPrice(19.99);

			try (Session session = factory.openSession()) {
				Transaction transaction = session.beginTransaction();

				// Salva o produto no banco de dados
				session.save(product);

				transaction.commit();
			}

			try (Session session = factory.openSession()) {
				// Recupera o produto do banco de dados usando o ID
				Product retrievedProduct = session.get(Product.class, product.getId());

				System.out.println("Produto recuperado: " + retrievedProduct.getName() + ", Preço: "
						+ retrievedProduct.getPrice());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(8000*1000); // Pausa por 10 segundos (pode ajustar conforme necessário)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```

