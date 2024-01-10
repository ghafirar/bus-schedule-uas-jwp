package com.ubl.busschedule.rest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ubl.busschedule.domain.Bus;
import com.ubl.busschedule.service.BusService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusController {
    private final BusService busService;

     @GetMapping("/buss")
    public String getBuss(Model model) {
        model.addAttribute("buss", busService.getBuss());
        return "buss";
    }

    
    @GetMapping("/signup-bus")
    public String showSignupForm(Bus bus) {
        return "add-bus";
    }

    @PostMapping("/buss")
    public String addBuss(@Valid Bus bus, BindingResult bindingResult, Model model) {
        String code = bus.getCode();

        boolean exists = busService.findBusByCode(code).isPresent();

        if (exists) {
            throw new IllegalArgumentException("bus with code:" + code + "is already exist");
        }

        busService.save(bus);

        model.addAttribute("buss", busService.getBuss());
        return "passengers";
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @GetMapping(value = "/buss/{code}")
    public ResponseEntity<Bus> findBus(@PathVariable("code") String code) {
        Optional<Bus> busOptional = busService.findBusByCode(code);
        if (busOptional.isPresent()) {
            return new ResponseEntity<>(busOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/buss/{code}")
    public String updateBus(@PathVariable("code") String code,
            Bus bus,
            BindingResult result, Model model) {

        final Optional<Bus> optionalBus = busService.findBusByCode(bus.getCode());
        if (optionalBus.isEmpty()) {
            throw new ServiceException("bus with code:" + code + "is not exists");
        }

        busService.update(bus);

        model.addAttribute("buss", busService.getBuss());
        return "redirect:/buss";
    }

    @GetMapping("/edit/{code}")
    public String showUpdateForm(@PathVariable("code") String code, Model model) {
        final Optional<Bus> optionalBus = busService.findBusByCode(code);
        if (optionalBus.isEmpty()) {
            throw new ServiceException("bus with code:" + code + "is not exists");
        }
        final Bus busToBeUpdated = optionalBus.get();

        model.addAttribute("bus", busToBeUpdated);
        return "update-bus";
    }

    @GetMapping(value = "/buss/{code}/delete")
    public String deleteBus(@PathVariable("code") String code) {
        busService.delete(code);
        return "redirect:/buss";
    }
}
