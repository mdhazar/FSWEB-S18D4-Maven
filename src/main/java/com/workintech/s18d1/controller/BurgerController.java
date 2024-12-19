package com.workintech.s18d1.controller;


import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping
    public Burger save (@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        burgerDao.save(burger);
        return burger;
    }

    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id) {
        return burgerDao.findById(id);
    }
    @PutMapping
    public Burger updateBurger (@RequestBody Burger burger) {
        return burgerDao.update(burger);
    }
    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable Long id) {
        return  burgerDao.remove(id);
    }
    @GetMapping("/breadType/{breadType}")
    public List<Burger> getByBread (@PathVariable("breadType") String breadType) {
        BreadType btenum = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(btenum);
    }
    @GetMapping("/price/{price}")
    public List<Burger> getByPrice (@PathVariable("price") Integer price) {
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/content/{content}")
    public List<Burger> getByContent (@PathVariable("content") String content) {
        return burgerDao.findByContent(content);
    }
}