#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.converter;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ${package}.converter.mocks.MockBook;
import ${package}.converter.mocks.MockPerson;
import ${package}.data.model.Book;
import ${package}.data.model.Person;
import ${package}.data.vo.v1.BookVO;
import ${package}.data.vo.v1.PersonVO;

public class DozerConverterTest {

	MockPerson inputObject;
	MockBook inputBookObject;
	
	@Before
	public void setUp() { 
		inputObject = new MockPerson();
		inputBookObject = new MockBook();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("First Name Test - 0", output.getFirstName());
		Assert.assertEquals("Last Name Test - 0", output.getLastName());
		Assert.assertEquals("Addres Test - 0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseBookEntityToVOTest() {
		BookVO output = DozerConverter.parseObject(inputBookObject.mockEntity(), BookVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("Author Name Test - 0", output.getAuthor());
		Assert.assertEquals("Title Name Test - 0", output.getTitle());
		Assert.assertEquals(Double.valueOf(10), output.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(0L)), output.getLaunchDate());
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("First Name Test - 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test - 0", outputZero.getLastName());
		Assert.assertEquals("Addres Test - 0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("First Name Test - 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test - 7", outputSeven.getLastName());
		Assert.assertEquals("Addres Test - 7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("First Name Test - 12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test - 12", outputTwelve.getLastName());
		Assert.assertEquals("Addres Test - 12", outputTwelve.getAddress());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}
	
	@Test
	public void parseBookEntityListToVOListTest() {
		List<BookVO> outputList = DozerConverter.parseListObjects(inputBookObject.mockEntityList(), BookVO.class);
		BookVO outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("Author Name Test - 0", outputZero.getAuthor());
		Assert.assertEquals("Title Name Test - 0", outputZero.getTitle());
		Assert.assertEquals(Double.valueOf(10), outputZero.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(0L)), outputZero.getLaunchDate());
		
		BookVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("Author Name Test - 7", outputSeven.getAuthor());
		Assert.assertEquals("Title Name Test - 7", outputSeven.getTitle());
		Assert.assertEquals(Double.valueOf(20), outputSeven.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(7L)), outputSeven.getLaunchDate());
		
		BookVO outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("Author Name Test - 12", outputTwelve.getAuthor());
		Assert.assertEquals("Title Name Test - 12", outputTwelve.getTitle());
		Assert.assertEquals(Double.valueOf(10), outputTwelve.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(12L)), outputTwelve.getLaunchDate());
	}
	
	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name Test - 0", output.getFirstName());
		Assert.assertEquals("Last Name Test - 0", output.getLastName());
		Assert.assertEquals("Addres Test - 0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseVOBookToEntityTest() {
		Book output = DozerConverter.parseObject(inputBookObject.mockVO(), Book.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("Author Name Test - 0", output.getAuthor());
		Assert.assertEquals("Title Name Test - 0", output.getTitle());
		Assert.assertEquals(Double.valueOf(10), output.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(0L)), output.getLaunchDate());
	}
	
	@Test
	public void parseVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name Test - 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test - 0", outputZero.getLastName());
		Assert.assertEquals("Addres Test - 0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		Person outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name Test - 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test - 7", outputSeven.getLastName());
		Assert.assertEquals("Addres Test - 7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());
		
		Person outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("First Name Test - 12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test - 12", outputTwelve.getLastName());
		Assert.assertEquals("Addres Test - 12", outputTwelve.getAddress());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}
	
	@Test
	public void parseVOBookListToEntityListTest() {
		List<Book> outputList = DozerConverter.parseListObjects(inputBookObject.mockVOList(), Book.class);
		Book outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("Author Name Test - 0", outputZero.getAuthor());
		Assert.assertEquals("Title Name Test - 0", outputZero.getTitle());
		Assert.assertEquals(Double.valueOf(10), outputZero.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(0L)), outputZero.getLaunchDate());
		
		Book outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("Author Name Test - 7", outputSeven.getAuthor());
		Assert.assertEquals("Title Name Test - 7", outputSeven.getTitle());
		Assert.assertEquals(Double.valueOf(20), outputSeven.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(7L)), outputSeven.getLaunchDate());
		
		Book outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("Author Name Test - 12", outputTwelve.getAuthor());
		Assert.assertEquals("Title Name Test - 12", outputTwelve.getTitle());
		Assert.assertEquals(Double.valueOf(10), outputTwelve.getPrice());
		Assert.assertEquals(new Date(Long.valueOf(12L)), outputTwelve.getLaunchDate());
	}
}
