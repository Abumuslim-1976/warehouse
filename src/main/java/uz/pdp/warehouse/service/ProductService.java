package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public Result addProduct(ProductDto productDto) {
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists)
            return new Result("Bunday product bu categoriyaga qo`shilgan", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bundnay categoriya mavjud emas", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o`lchov birligi mavjud emas", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday photo mavjud emas", false);

        String code = UUID.randomUUID().toString();

        Product product = new Product();
        product.setCode(code);
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachmentPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("product saqlandi", true);
    }


    public List<Product> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        }
        return new Product();
    }


    public Result deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday ID li product yo`q", false);

        productRepository.deleteById(id);
        return new Result("Product o`chirildi", true);
    }


    public Result editProduct(Integer editProductId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(editProductId);
        if (optionalProduct.isPresent()) {
            boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
            if (exists)
                return new Result("Bunday name va category ID bor", false);

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday Categoriya mavjud emas", false);

            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
            if (!optionalMeasurement.isPresent())
                return new Result("Bunday measurement mavjud", false);

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (!optionalAttachment.isPresent())
                return new Result("Bunday photo mavjud emas", false);

            String code = UUID.randomUUID().toString();

            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setCode(code);
            product.setCategory(optionalCategory.get());
            product.setMeasurement(optionalMeasurement.get());
            product.setAttachmentPhoto(optionalAttachment.get());
            productRepository.save(product);
            return new Result("Product saqlandi", true);
        }
        return new Result("Product topilmadi", false);
    }

}
