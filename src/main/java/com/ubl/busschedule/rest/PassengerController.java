package com.ubl.busschedule.rest;

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


import com.ubl.busschedule.domain.Passenger;
import com.ubl.busschedule.service.PassengerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

     @GetMapping("/passengers")
    public String getPassenger(Model model) {
        model.addAttribute("passengers", passengerService.getPassengers());
        return "passengers";
    }

    
    @GetMapping("/signup-passenger")
    public String showSignupForm(Passenger passenger) {
        return "add-passenger";
    }

    @PostMapping("/passengers")
    public String addPassenger(@Valid Passenger passenger, BindingResult bindingResult, Model model) {
        String passid = passenger.getPassId();

        boolean exists = passengerService.findPassengerByPassId(passid).isPresent();

        if (exists) {
            throw new IllegalArgumentException("passenger with passid:" + passid + "is already exist");
        }

        passengerService.save(passenger);

        model.addAttribute("passengers", passengerService.getPassengers());
        return "passengers";
    }


    @GetMapping(value = "/passengers/{passid}")
    public ResponseEntity<Passenger> findPassenger(@PathVariable("passid") String passid) {
        Optional<Passenger> passengerOptional = passengerService.findPassengerByPassId(passid);
        if (passengerOptional.isPresent()) {
            return new ResponseEntity<>(passengerOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/passengers/{passid}")
    public String updatePassenger(@PathVariable("passid") String passid,
            Passenger passenger,
            BindingResult result, Model model) {

        final Optional<Passenger> optionalPassenger = passengerService.findPassengerByPassId(passenger.getPassId());
        if (optionalPassenger.isEmpty()) {
            throw new ServiceException("passenger with passid:" + passid + "is not exists");
        }

        passengerService.update(passenger);

        model.addAttribute("passengers", passengerService.getPassengers());
        return "redirect:/passengers";
    }

    @GetMapping("passengers/edit/{passid}")
    public String showUpdateForm(@PathVariable("passid") String passid, Model model) {
        final Optional<Passenger> optionalPassenger = passengerService.findPassengerByPassId(passid);
        if (optionalPassenger.isEmpty()) {
            throw new ServiceException("passenger with passid:" + passid + "is not exists");
        }
        final Passenger passengerToBeUpdated = optionalPassenger.get();

        model.addAttribute("passenger", passengerToBeUpdated);
        return "update-passenger";
    }

    @GetMapping(value = "/passengers/{passid}/delete")
    public String deletePassenger(@PathVariable("passid") String passid) {
        passengerService.delete(passid);
        return "redirect:/passengers";
    }

    
}
