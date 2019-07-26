package net.test.bookmanager.DAO;

import net.test.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {

        Session session = this.sessionFactory.getCurrentSession();
        session.save(book);
        logger.info("Book successfully saved. Book details " + book);

    }

    @Override
    public void updateBook(Book book) {

        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully update. Book details " + book);

    }

    @Override
    public void removeBook(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, new Integer(id));

        if(book != null) {

            session.delete(book);
            logger.info("Book successfully update. Book details " + book);
        }
        logger.info("Library dont have this book " + book);

    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, new Integer(id));

        logger.info("Book successfully loaded. Book details " + book);

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = this.sessionFactory.getCurrentSession();
       List<Book> bookList = session.createQuery("from Book").list();

        bookList.stream().forEach(book -> logger.info("Book list: " + book));

        return bookList;
    }
}
