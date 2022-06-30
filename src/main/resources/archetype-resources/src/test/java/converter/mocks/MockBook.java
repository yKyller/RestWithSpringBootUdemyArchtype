#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.converter.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ${package}.data.model.Book;
import ${package}.data.vo.v1.BookVO;

public class MockBook {


	public Book mockEntity() {
		return mockEntity(0);
	}
	
	public BookVO mockVO() {
		return mockVO(0);
	}
	
	public List<Book> mockEntityList() {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 14; i++) {
			books.add(mockEntity(i));
		}
		return books;
	}
	
	public List<BookVO> mockVOList() {
		List<BookVO> books = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			books.add(mockVO(i));
		}
		return books;
	}
	
	private Book mockEntity(Integer number) {
		Book book = new Book();
		book.setAuthor("Author Name Test - " + number);
		book.setTitle("Title Name Test - " + number);
		book.setPrice(((number % 2)==0) ? Double.valueOf(10) : Double.valueOf(20));
		book.setId(number.longValue());
		book.setLaunchDate(new Date(number.longValue()));
		return book;
	}
	
	private BookVO mockVO(Integer number) {
		BookVO book = new BookVO();
		book.setAuthor("Author Name Test - " + number);
		book.setTitle("Title Name Test - " + number);
		book.setPrice(((number % 2)==0) ? Double.valueOf(10) : Double.valueOf(20));
		book.setKey(number.longValue());
		book.setLaunchDate(new Date(number.longValue()));
		return book;
	}
}
