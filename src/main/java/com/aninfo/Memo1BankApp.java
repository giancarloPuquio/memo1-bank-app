package com.aninfo;

import com.aninfo.model.Account;
import com.aninfo.model.Transaccion;
import com.aninfo.service.AccountService;
import com.aninfo.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class Memo1BankApp {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransaccionService transaccionService;

	public static void main(String[] args) {
		SpringApplication.run(Memo1BankApp.class, args);
	}

	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@PostMapping("/transaccion")
	@ResponseStatus(HttpStatus.CREATED)
	public Transaccion createTransaccion(@RequestBody Transaccion transaccion) {
		return transaccionService.createTransaccion(transaccion);
	}

	@GetMapping("/accounts")
	public Collection<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/accounts/{cbu}")
	public ResponseEntity<Account> getAccount(@PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);
		return ResponseEntity.of(accountOptional);
	}

	@GetMapping("/transaccion")
	public Collection<Transaccion> getTransaccions() {
		return transaccionService.getTransaccions();
	}

	@PutMapping("/accounts/{cbu}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);

		if (!accountOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		account.setCbu(cbu);
		accountService.save(account);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/transaccion/{id}")
	public ResponseEntity<Transaccion> getTransaccion(@PathVariable Long id) {
		Optional<Transaccion> transaccionOptional = transaccionService.findById(id);
		return ResponseEntity.of(transaccionOptional);
	}

	/*
        @GetMapping("/transaccion/{cbuCuenta}")
        public ResponseEntity<Transaccion> getCuenta(@PathVariable Long cbuCuenta) {
            Optional<Transaccion> transaccionOptional = transaccionService.findById(cbuCuenta);
            return ResponseEntity.of(transaccionOptional);
        }*/

	@DeleteMapping("/accounts/{cbu}")
	public void deleteAccount(@PathVariable Long cbu) {
		accountService.deleteById(cbu);
	}

	@DeleteMapping("/transaccions/{id}")
	public void deleteTransaccion(@PathVariable Long id) {
		transaccionService.deleteById(id);
	}

	@PutMapping("/accounts/{cbu}/withdraw")
	public Account withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
		return accountService.withdraw(cbu, sum);
	}

	@PutMapping("/accounts/{cbu}/deposit")
	public Account deposit(@PathVariable Long cbu, @RequestParam Double sum) {
		return accountService.deposit(cbu, sum);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
