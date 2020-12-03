package repository;


import model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Iterable<Customer> findAllByNameContainsOrAuthorContains(String name, String author);

    Page<Customer> findAllByNameContainsOrAuthorContains(String name, String author,Pageable pageInfo);
}
