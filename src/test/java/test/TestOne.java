package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestOne {

	@Mock
	private InputStream isOne;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(this.isOne.available()).thenThrow(new IOException("bbb"));
		doThrow(new IOException("aaa")).when(isOne).close();
	}

	@Test
	public void name() {
		try (InputStream iis = this.isOne) {
			System.out.println(iis.available());
		} catch (Exception e) {
			System.err.println(e);
			System.err.println(e.getSuppressed()[0]);
		}

	}
}