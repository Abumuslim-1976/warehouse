package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Result addCategory(CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByNameAndParentCategoryId(categoryDto.getName(), categoryDto.getParentCategoryId());
        if (exists)
            return new Result("Bunday nomli categoriya bor", false);

        Category category = new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday ota categoriya mavjud emas", true);

            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }


    public List<Category> getCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }


    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return category;
        }
        return new Category();
    }


    public Result deleteById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Bunday ID li categoriya yo`q", false);

        categoryRepository.deleteById(id);
        return new Result("categoriya o`chirildi", true);
    }


    public Result editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday ID li categoriya mavjud emas", false);

        boolean exists = categoryRepository.existsByNameAndParentCategoryId(categoryDto.getName(), categoryDto.getParentCategoryId());
        if (exists)
            return new Result("Bunday nomli categoriya bor", false);

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> parentCategoryById = categoryRepository.findById(categoryDto.getParentCategoryId());

            if (category.getParentCategory() != null) {
                category.setParentCategory(parentCategoryById.get());
            }
        }
        categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

}
