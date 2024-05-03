package com.productservices.productservices.Services;

import com.productservices.productservices.clients.FakeStoreClient;
import com.productservices.productservices.clients.FakeStoreProductDto;
import com.productservices.productservices.dtos.ProductDto;
import com.productservices.productservices.models.Category;
import com.productservices.productservices.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FakeStoreProductServicesImpl implements ProductServices{

    private RestTemplateBuilder restTemplateBuilder;
	private FakeStoreClient fakeStoreClient;

      public FakeStoreProductServicesImpl (RestTemplateBuilder restTemplateBuilder , FakeStoreClient fakeStoreClient){
		  this.restTemplateBuilder=restTemplateBuilder;
		  this.fakeStoreClient=fakeStoreClient;
	  }

	public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
											   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate=restTemplateBuilder.build();
		RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
		ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
		return (restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables));
	}
	private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto  productDto){
		Product product=new Product();
		product.setId(productDto.getId());
		product.setTitle(productDto.getTitle());
		product.setPrice(productDto.getPrice());
		Category category=new Category();
		category.setName(productDto.getCategory());
		product.setImageUrl(productDto.getImage());
		return  product;
	}
	@Override
	public List<Product> getAllProducts() {
		  List<FakeStoreProductDto>fakeStoreProductDtos=fakeStoreClient.getAllProducts();
		  List<Product> answer =new ArrayList<>();
		  for(FakeStoreProductDto productDto : fakeStoreProductDtos){
//			ProductDto productDto=new ProductDto();
			//this below line of code has method convertFakeStoreProductDtoToProduct
			//helps in converting raw data to product objects
			answer.add(convertFakeStoreProductDtoToProduct(productDto));  //
		  }
		  return answer;
	}



	/*
	BELOW CODE
	return a product object with all the details of the fetched products with
	the id of the category will be null but the name if the category shall be correct
	 */
	@Override
	public Optional<Product> getSingleProduct(Long productId) {
		Optional<FakeStoreProductDto>productDto=fakeStoreClient.getSingleProduct(productId);
		if(productDto.isPresent()){
			FakeStoreProductDto responce=productDto.get();
			return Optional.of(convertFakeStoreProductDtoToProduct(responce));
		}else{
			return Optional.empty();
		}
	}

	@Override
	public Product addNewProduct(ProductDto product) {
		 FakeStoreProductDto productDto=fakeStoreClient.addNewProduct(product);
		 return convertFakeStoreProductDtoToProduct(productDto);
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		RestTemplate restTemplate=restTemplateBuilder.build();
		FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
		fakeStoreProductDto.setDescription(product.getDescription());
		fakeStoreProductDto.setTitle(product.getTitle());
		fakeStoreProductDto.setPrice(product.getPrice());
		fakeStoreProductDto.setImage(product.getImageUrl());
		fakeStoreProductDto.setCategory(product.getCategory().getName());
		ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=requestForEntity(
				HttpMethod.PATCH,
				"https://fakestoreapi.com/products/{id}",
				fakeStoreProductDto,
				FakeStoreProductDto.class,
				productId
		);
		return  convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());

	}


	@Override
	public String replaceProduct(Long productId, Product product) {

		return null;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		return false;
	}
}
