package com.novation.eligibility.ui.web.sep;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import com.novation.eligibility.ui.services.DummyPriceRepository;
import com.novation.eligibility.ui.web.model.Price;

@Controller
@RequestMapping("/pricelist")
public class PriceServiceEndpoint {

	
	private static final Logger log = LoggerFactory.getLogger(PriceServiceEndpoint.class);

	
	@Autowired
	DummyPriceRepository priceRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Price> list() {

		log.info("Reached price controller -> query the price list: " + priceRepository.getPriceList());

		return priceRepository.getPriceList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Price find(@PathVariable("id") Integer id) {
		return priceRepository.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePrice(@PathVariable("id") Integer id,
			@RequestBody Price price) {
		log.info("Put price called for price: " + price);
		priceRepository.update(price);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	public HttpEntity<?> create(@RequestBody Price price,
			@Value("#{request.requestURL}") StringBuffer parentUri) {
		log.info("create price called for price: " + price);
		price = priceRepository.save(price);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(childLocation(parentUri, price.getName()));
		return new HttpEntity<Object>(headers);
	}

	private URI childLocation(StringBuffer parentUri, Object childId) {
		UriTemplate uri = new UriTemplate(parentUri.append("/{childId}")
				.toString());
		return uri.expand(childId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		log.info("Delete called for id: " + id);
		priceRepository.delete(id);
	}

	@RequestMapping(value = "/removeAll", method = RequestMethod.DELETE)
	public @ResponseBody
	void removeAllPrices() {
		log.info("Delete all called");
		priceRepository.deleteAll();
	}

}
