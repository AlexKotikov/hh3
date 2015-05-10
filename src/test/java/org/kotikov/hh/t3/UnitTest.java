package org.kotikov.hh.t3;

 
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest  {

 
	
	private Date de;	
	private SmartPhone a ;
	private SmartPhone b ;
	private SmartPhone c ;
	private SmartPhone d ;
	
	private SmartPhone nota1 ;
	private SmartPhone nota2 ;
	private SmartPhone nota3 ;

	@Before
	public void setUp() throws Exception { 
	  
	  de = new Date();	
	  de.setTime((long)10000);
	  
      a = new SmartPhone("cx-1001",de,"1000");
      b = new SmartPhone("cx-1001",de,"1000");
      c = a;
      d = new SmartPhone("cx-1001",de,"1000");
      
      // not a     
      nota1 = new SmartPhone(null,de,"1000");
      nota2 = new SmartPhone("cx-1001",new Date(),"1000");
      nota3 = new SmartPhone("cx-1001",de,null);
	}
	
	@Test
	/**
	 * тесирование с помощью тула EqualsTester
	 * 
	 * Это специальный тул для equals() + hashcode()
	 * тул удобен тем что содержит проверки на все основные свойства (транзитивность, симметричность, не null и тд):
	 * http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)
	 * в комплексе и таким образом все можно протестировать в одном методе.
	 */
	public void testWithET() {	
		EqualsTester<SmartPhone> equalsTester = EqualsTester.newInstance(a) ; 
	    
		equalsTester.assertImplementsEqualsAndHashCode(); //both
	    equalsTester.assertEqual(a,b);
	    equalsTester.assertEqual(a,b,d);
	    equalsTester.assertEqual(a,b,c);
	    
	    equalsTester.assertNotEqual(a,nota1);
	    equalsTester.assertNotEqual(a,nota2);
	    equalsTester.assertNotEqual(a,nota3);       
	}
	
	
    // Если тестировать без тула	
	@Test
	public void reflexive() {	 
		assertTrue("Объект не соответствует сам себе", a.equals(a));  	 
	}

	@Test
	public void reflexive2() {	 
		
		for (int i=0; i<10;i++) {
			assertTrue("Объект не соответствует сам себе в одном и 10 случаев", a.equals(a));  	
		}
			 
	}
	
	@Test
	public void castError() { 
	    assertFalse("Соответствует классу Object", a.equals(new Object()));
	    assertFalse("Соответствует классу String", a.equals(new String()));
	}
	
	@Test
	public void equalsNull() { 
		assertFalse("Соответствует  null", a.equals(null));
	}
	
	@Test
	public void ownLink() { 
		assertTrue("Объект не соответствует сам себе по ссылке", a.equals(c));
	}

	@Test
	public void symmetric() { 
		String msg = "Симметричность";
		assertTrue(msg, a.equals(b));
		assertTrue(msg, b.equals(a));
	}
	
	@Test
	public void transitive() { 
		String msg = "Транзитивность";
		assertTrue(msg, a.equals(b));
		assertTrue(msg, b.equals(d));
		assertTrue(msg, d.equals(a));
	}
	
	@Test
	public void notSimularity() {	 
		
		for (int i=0; i<10;i++) {
			String msg = "Ложное соответстве разных объектов";
			assertFalse(msg, a.equals(nota1)); 
			assertFalse(msg, a.equals(nota2)); 
			assertFalse(msg, a.equals(nota3)); 
		}
			 
	}
	
	@Test
    public void testHashcode() {
		for (int i=0; i<5000;i++)   {			 
		    assertEquals("Хэш неодинаков для одного и того же объекта", a.hashCode(),  a.hashCode());
		}
    }
	
	@Test
    public void testHashcode2() {
 			 
		for (int i=0; i<5000;i++)   {	 
		    assertEquals("Хэш неодинаков для двух одинаковых объектов", a.hashCode(),  b.hashCode());
		    
		}
    }
	
	@Test
    public void testHashcode3() {
 			 
		for (int i=0; i<5000;i++)   {	 
			String msg = "Хэш одинаков для разных объектов";
			
			assertFalse(msg, a.hashCode() ==  nota1.hashCode());
			assertFalse(msg, a.hashCode() ==  nota2.hashCode());
			assertFalse(msg, a.hashCode() ==  nota3.hashCode());
		}
	
   }	
}
	