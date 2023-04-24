package vttp.ssf.addressbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import vttp.ssf.addressbook.controllers.AddressBookController;

@SpringBootTest
class AddressbookApplicationTests {

	@Autowired
	private AddressBookController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
