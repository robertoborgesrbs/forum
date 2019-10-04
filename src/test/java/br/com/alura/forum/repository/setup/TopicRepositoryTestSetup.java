package br.com.alura.forum.repository.setup;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.topic.domain.Topic;

public class TopicRepositoryTestSetup {

	private TestEntityManager testEntityManager;
	
	public TopicRepositoryTestSetup(TestEntityManager testEntityManager) {
		this.testEntityManager = testEntityManager;
	}
	
	public void openTopicsByCategorySetup() {
		Category programacao = this.testEntityManager.persist(new Category("Programacao"));
		
		Category front = this.testEntityManager.persist(new Category("Front-end"));
		
		Category javaWeb = this.testEntityManager.persist(new Category("Java Web", programacao));
		
		Category java = this.testEntityManager.persist(new Category("Java", front));
		
		Course fj27 = this.testEntityManager.persist(new Course("Spring Framework", javaWeb));
		
		Course fj21 = this.testEntityManager.persist(new Course("Servlet API e MVC", javaWeb));
		
		Course fj46 = this.testEntityManager.persist(new Course("React", java));
		
		Topic springTopic = new Topic("Topico Spring", "Conteudo do Topico", null, fj27);
		
		Topic servletTopic = new Topic("Topico Servlet", "Conteudo do Topico", null, fj21);
		
		Topic reactTopic = new Topic("Topico React", "Conteudo do Topico", null, fj46);
		
		this.testEntityManager.persist(springTopic);
		this.testEntityManager.persist(servletTopic);
		this.testEntityManager.persist(reactTopic);
	}

}

