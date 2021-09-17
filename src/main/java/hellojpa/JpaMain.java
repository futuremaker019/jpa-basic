package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 비영속
/*            Member member = new Member();
            member.setId(1L);
            member.setName("helloA");*/

            // 영속 (db에 저장이 안되고 영속성 컨텍스트에 저장됨)
//            em.persist(member);

            // 값을 찾아와서 변경하는것만으로 수정이 이루어진다.
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloMember");

            // 커밋하는 순간에 db에 저장한다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
