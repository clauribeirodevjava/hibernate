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
