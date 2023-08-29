package com.example.managerProject.controllers;

import com.example.managerProject.entities.Agreement;
import com.example.managerProject.entities.Client;
import com.example.managerProject.entities.LoanApplication;
import com.example.managerProject.services.AgreementService;
import com.example.managerProject.services.ApplicationService;
import com.example.managerProject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Random;

@Controller
public class MainController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ApplicationService appService;
    @Autowired
    private AgreementService agreementService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Главная страница");
        return "index";
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", clientService.findAllClients());
        return "clients";
    }

    @GetMapping(value = "/find_clients", params = "btn_find_by_fio")
    public String showClientsByFIO(@RequestParam String find_by_fio, Model model) {
        model.addAttribute("clients", clientService.findClientsByFIO(find_by_fio));
        return "clients";
    }

    @GetMapping(value = "/find_clients", params = "btn_find_by_phone")
    public String showClientsByPhone(@RequestParam String find_by_phone, Model model) {
        model.addAttribute("clients", clientService.findClientsByPhone(find_by_phone));
        return "clients";
    }

    @GetMapping(value = "/find_clients", params = "btn_find_by_passport")
    public String showClientsByPassport(@RequestParam String find_by_passport, Model model) {
        model.addAttribute("clients", clientService.findClientByPassportAsList(find_by_passport));
        return "clients";
    }

    @GetMapping("/approved")
    public String showApprovedApplications(Model model) {
        model.addAttribute("applications", appService.findApprovedApplications());
        model.addAttribute("title", "ЗАЯВКИ СО СТАТУСОМ ОДОБРЕНИЯ");
        return "applications";
    }

    @GetMapping(value = "/signed")
    public String showSignedAgreements(Model model) {
        model.addAttribute("agreements", agreementService.findSignedAgreements());
        model.addAttribute("title", "ПОДПИСАННЫЕ КРЕДИТНЫЕ ДОГОВОРЫ");
        return "agreements";
    }

    @GetMapping("/applications")
    public String applications(Model model) {
        model.addAttribute("applications", appService.findAllApplications());
        model.addAttribute("title", "ЗАЯВКИ");
        return "applications";
    }

    @GetMapping("/applications/{id}")
    public String openApplication(@PathVariable(value = "id") Long id, Model model) {
        LoanApplication application = appService.findApplicationByID(id);
        model.addAttribute("have_agreement", Boolean.toString(agreementService.haveAgreementByApplicationID(id)));
        model.addAttribute("agreementID", agreementService.findAgreementByApplicationID(id));
        model.addAttribute("id", id);
        model.addAttribute("status", application.getStatus());
        model.addAttribute("period", application.getPeriod());
        model.addAttribute("sum", application.getSum());
        model.addAttribute("fio", application.getClient_id().getFio());
        model.addAttribute("passport", application.getClient_id().getPassport());
        model.addAttribute("family_status", application.getClient_id().getFamily_status());
        model.addAttribute("address", application.getClient_id().getAddress());
        model.addAttribute("phone", application.getClient_id().getPhone_number());
        model.addAttribute("job_title", application.getClient_id().getJob_title());
        model.addAttribute("job_organization", application.getClient_id().getJob_organization());
        model.addAttribute("job_start", application.getClient_id().getJob_period_start());
        model.addAttribute("job_finish", application.getClient_id().getJob_period_finish());
        return "opened_application";
    }

    @GetMapping("/agreements")
    public String agreements(Model model) {
        model.addAttribute("agreements", agreementService.findAllAgreements());
        model.addAttribute("title", "КРЕДИТНЫЕ ДОГОВОРЫ");
        return "agreements";
    }

    @GetMapping("/agreements/{id}")
    public String openAgreement(@PathVariable(value = "id") Long id, Model model) {
        Agreement agreement = agreementService.findAgreementByID(id);
        LoanApplication application = agreement.getApplication_id();
        model.addAttribute("agreement_id", id);
        model.addAttribute("app_id", application.getId());
        model.addAttribute("status", application.getStatus());
        model.addAttribute("period", application.getPeriod());
        model.addAttribute("sum", application.getSum());
        model.addAttribute("fio", application.getClient_id().getFio());
        model.addAttribute("passport", application.getClient_id().getPassport());
        model.addAttribute("family_status", application.getClient_id().getFamily_status());
        model.addAttribute("address", application.getClient_id().getAddress());
        model.addAttribute("phone", application.getClient_id().getPhone_number());
        model.addAttribute("job_title", application.getClient_id().getJob_title());
        model.addAttribute("job_organization", application.getClient_id().getJob_organization());
        model.addAttribute("job_start", application.getClient_id().getJob_period_start());
        model.addAttribute("job_finish", application.getClient_id().getJob_period_finish());
        model.addAttribute("signing_date", agreement.getSigning_date());
        model.addAttribute("signing_status", agreement.getStatus());
        return "opened_agreement";
    }

    @GetMapping("/opened_agreement")
    public String open_agreement(@RequestParam(name = "id") Long app_id, Model model) {
        LoanApplication application = appService.findApplicationByID(app_id);
        model.addAttribute("agreement_id", "");
        model.addAttribute("app_id", app_id);
        model.addAttribute("status", application.getStatus());
        model.addAttribute("period", application.getPeriod());
        model.addAttribute("sum", application.getSum());
        model.addAttribute("fio", application.getClient_id().getFio());
        model.addAttribute("passport", application.getClient_id().getPassport());
        model.addAttribute("family_status", application.getClient_id().getFamily_status());
        model.addAttribute("address", application.getClient_id().getAddress());
        model.addAttribute("phone", application.getClient_id().getPhone_number());
        model.addAttribute("job_title", application.getClient_id().getJob_title());
        model.addAttribute("job_organization", application.getClient_id().getJob_organization());
        model.addAttribute("job_start", application.getClient_id().getJob_period_start());
        model.addAttribute("job_finish", application.getClient_id().getJob_period_finish());
        model.addAttribute("signing_date", "");
        model.addAttribute("signing_status", "Не подписан");
        return "opened_agreement";
    }

    @PostMapping(value = "/opened_agreement", params = "save_with_sign")
    public String saveSigningAgreement(@RequestParam(name = "id") Long app_id, Model model) {
        LoanApplication application = appService.findApplicationByID(app_id);
        Agreement agreement = new Agreement();
        agreement.setApplication_id(application);
        agreement.setSigning_date(LocalDate.now());
        agreement.setStatus("Подписан");
        agreementService.saveAgreement(agreement);
        return "redirect:/";
    }

    @PostMapping(value = "/opened_agreement", params = "save_without_sign")
    public String saveNoSigningAgreement(@RequestParam(name = "id") Long app_id, Model model) {
        LoanApplication application = appService.findApplicationByID(app_id);
        Agreement agreement = new Agreement();
        agreement.setApplication_id(application);
        agreement.setStatus("Не подписан");
        agreementService.saveAgreement(agreement);
        return "redirect:/agreements";
    }

    @PostMapping(value = "/agreement_for_update")
    public String updateAndSaveAgreement(@RequestParam(name = "agree_id") Long agree_id, Model model) {
        Agreement agreement = agreementService.findAgreementByID(agree_id);
        agreement.setSigning_date(LocalDate.now());
        agreement.setStatus("Подписан");
        agreementService.updateAgreement(agreement);
        return "redirect:/";
    }

    @GetMapping("/create_application")
    public String create_application(Model model) { return "create_application"; }

    @PostMapping("/create_application")
    public String save_application(@RequestParam String fio, @RequestParam String passport,
                                   @RequestParam String family_status, @RequestParam String address,
                                   @RequestParam String phone_number, @RequestParam String job_title,
                                   @RequestParam String job_organization, @RequestParam String job_period_start,
                                   @RequestParam String job_period_finish, @RequestParam String credit_sum,
                                   Model model) {
        Client client;
        if (clientService.findClientByPassport(passport) == null) {
            client = new Client(fio, passport, family_status, address, phone_number, job_title, job_organization,
                    job_period_start, job_period_finish);
            clientService.saveClient(client);
        }
        else client = clientService.findClientByPassport(passport);

        int sum = Integer.parseInt(credit_sum);
        LoanApplication application = new LoanApplication(client, getRandomStatus(), getRandomPeriod(), sum);
        appService.saveApplication(application);
        return "redirect:/";
    }

    private String getRandomStatus() {
        Random random = new Random();
        boolean result = random.nextBoolean();
        if (result) return "Одобрен";
        else return "Не одобрен";
    }

    private int getRandomPeriod() {
        Random random = new Random();
        int month = random.nextInt(13 - 1) + 1;
        return month * 30;
    }
}
