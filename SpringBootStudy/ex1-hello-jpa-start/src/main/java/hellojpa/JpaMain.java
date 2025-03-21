package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
            for(Member member : result) {
                System.out.println("member = "+ member.getName());
            }

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
