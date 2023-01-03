/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lk.java.entity.Insurance;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import lk.java.controller.exceptions.IllegalOrphanException;
import lk.java.controller.exceptions.NonexistentEntityException;
import lk.java.controller.exceptions.RollbackFailureException;
import lk.java.entity.DisciplinaryHistory;
import lk.java.entity.Shift;
import lk.java.entity.Tax;
import lk.java.entity.Salary;
import lk.java.entity.Bank;
import lk.java.entity.Employee;
import lk.java.entity.PerformanceFeedback;
import lk.java.entity.Job;
import lk.java.entity.TimeOffRequest;

/**
 *
 * @author Rajitha Yasasri
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) throws RollbackFailureException, Exception {
        if (employee.getInsuranceList() == null) {
            employee.setInsuranceList(new ArrayList<Insurance>());
        }
        if (employee.getDisciplinaryHistoryList() == null) {
            employee.setDisciplinaryHistoryList(new ArrayList<DisciplinaryHistory>());
        }
        if (employee.getShiftList() == null) {
            employee.setShiftList(new ArrayList<Shift>());
        }
        if (employee.getTaxList() == null) {
            employee.setTaxList(new ArrayList<Tax>());
        }
        if (employee.getSalaryList() == null) {
            employee.setSalaryList(new ArrayList<Salary>());
        }
        if (employee.getBankList() == null) {
            employee.setBankList(new ArrayList<Bank>());
        }
        if (employee.getPerformanceFeedbackList() == null) {
            employee.setPerformanceFeedbackList(new ArrayList<PerformanceFeedback>());
        }
        if (employee.getJobList() == null) {
            employee.setJobList(new ArrayList<Job>());
        }
        if (employee.getTimeOffRequestList() == null) {
            employee.setTimeOffRequestList(new ArrayList<TimeOffRequest>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Insurance> attachedInsuranceList = new ArrayList<Insurance>();
            for (Insurance insuranceListInsuranceToAttach : employee.getInsuranceList()) {
                insuranceListInsuranceToAttach = em.getReference(insuranceListInsuranceToAttach.getClass(), insuranceListInsuranceToAttach.getIdinsurance());
                attachedInsuranceList.add(insuranceListInsuranceToAttach);
            }
            employee.setInsuranceList(attachedInsuranceList);
            List<DisciplinaryHistory> attachedDisciplinaryHistoryList = new ArrayList<DisciplinaryHistory>();
            for (DisciplinaryHistory disciplinaryHistoryListDisciplinaryHistoryToAttach : employee.getDisciplinaryHistoryList()) {
                disciplinaryHistoryListDisciplinaryHistoryToAttach = em.getReference(disciplinaryHistoryListDisciplinaryHistoryToAttach.getClass(), disciplinaryHistoryListDisciplinaryHistoryToAttach.getIddisciplinaryHistory());
                attachedDisciplinaryHistoryList.add(disciplinaryHistoryListDisciplinaryHistoryToAttach);
            }
            employee.setDisciplinaryHistoryList(attachedDisciplinaryHistoryList);
            List<Shift> attachedShiftList = new ArrayList<Shift>();
            for (Shift shiftListShiftToAttach : employee.getShiftList()) {
                shiftListShiftToAttach = em.getReference(shiftListShiftToAttach.getClass(), shiftListShiftToAttach.getIdshift());
                attachedShiftList.add(shiftListShiftToAttach);
            }
            employee.setShiftList(attachedShiftList);
            List<Tax> attachedTaxList = new ArrayList<Tax>();
            for (Tax taxListTaxToAttach : employee.getTaxList()) {
                taxListTaxToAttach = em.getReference(taxListTaxToAttach.getClass(), taxListTaxToAttach.getIdtax());
                attachedTaxList.add(taxListTaxToAttach);
            }
            employee.setTaxList(attachedTaxList);
            List<Salary> attachedSalaryList = new ArrayList<Salary>();
            for (Salary salaryListSalaryToAttach : employee.getSalaryList()) {
                salaryListSalaryToAttach = em.getReference(salaryListSalaryToAttach.getClass(), salaryListSalaryToAttach.getIdsalary());
                attachedSalaryList.add(salaryListSalaryToAttach);
            }
            employee.setSalaryList(attachedSalaryList);
            List<Bank> attachedBankList = new ArrayList<Bank>();
            for (Bank bankListBankToAttach : employee.getBankList()) {
                bankListBankToAttach = em.getReference(bankListBankToAttach.getClass(), bankListBankToAttach.getIdbank());
                attachedBankList.add(bankListBankToAttach);
            }
            employee.setBankList(attachedBankList);
            List<PerformanceFeedback> attachedPerformanceFeedbackList = new ArrayList<PerformanceFeedback>();
            for (PerformanceFeedback performanceFeedbackListPerformanceFeedbackToAttach : employee.getPerformanceFeedbackList()) {
                performanceFeedbackListPerformanceFeedbackToAttach = em.getReference(performanceFeedbackListPerformanceFeedbackToAttach.getClass(), performanceFeedbackListPerformanceFeedbackToAttach.getIdperformanceFeedback());
                attachedPerformanceFeedbackList.add(performanceFeedbackListPerformanceFeedbackToAttach);
            }
            employee.setPerformanceFeedbackList(attachedPerformanceFeedbackList);
            List<Job> attachedJobList = new ArrayList<Job>();
            for (Job jobListJobToAttach : employee.getJobList()) {
                jobListJobToAttach = em.getReference(jobListJobToAttach.getClass(), jobListJobToAttach.getIdjob());
                attachedJobList.add(jobListJobToAttach);
            }
            employee.setJobList(attachedJobList);
            List<TimeOffRequest> attachedTimeOffRequestList = new ArrayList<TimeOffRequest>();
            for (TimeOffRequest timeOffRequestListTimeOffRequestToAttach : employee.getTimeOffRequestList()) {
                timeOffRequestListTimeOffRequestToAttach = em.getReference(timeOffRequestListTimeOffRequestToAttach.getClass(), timeOffRequestListTimeOffRequestToAttach.getIdtimeOffRequest());
                attachedTimeOffRequestList.add(timeOffRequestListTimeOffRequestToAttach);
            }
            employee.setTimeOffRequestList(attachedTimeOffRequestList);
            em.persist(employee);
            for (Insurance insuranceListInsurance : employee.getInsuranceList()) {
                Employee oldEmployeeIdemployeeOfInsuranceListInsurance = insuranceListInsurance.getEmployeeIdemployee();
                insuranceListInsurance.setEmployeeIdemployee(employee);
                insuranceListInsurance = em.merge(insuranceListInsurance);
                if (oldEmployeeIdemployeeOfInsuranceListInsurance != null) {
                    oldEmployeeIdemployeeOfInsuranceListInsurance.getInsuranceList().remove(insuranceListInsurance);
                    oldEmployeeIdemployeeOfInsuranceListInsurance = em.merge(oldEmployeeIdemployeeOfInsuranceListInsurance);
                }
            }
            for (DisciplinaryHistory disciplinaryHistoryListDisciplinaryHistory : employee.getDisciplinaryHistoryList()) {
                Employee oldEmployeeIdemployeeOfDisciplinaryHistoryListDisciplinaryHistory = disciplinaryHistoryListDisciplinaryHistory.getEmployeeIdemployee();
                disciplinaryHistoryListDisciplinaryHistory.setEmployeeIdemployee(employee);
                disciplinaryHistoryListDisciplinaryHistory = em.merge(disciplinaryHistoryListDisciplinaryHistory);
                if (oldEmployeeIdemployeeOfDisciplinaryHistoryListDisciplinaryHistory != null) {
                    oldEmployeeIdemployeeOfDisciplinaryHistoryListDisciplinaryHistory.getDisciplinaryHistoryList().remove(disciplinaryHistoryListDisciplinaryHistory);
                    oldEmployeeIdemployeeOfDisciplinaryHistoryListDisciplinaryHistory = em.merge(oldEmployeeIdemployeeOfDisciplinaryHistoryListDisciplinaryHistory);
                }
            }
            for (Shift shiftListShift : employee.getShiftList()) {
                Employee oldEmployeeIdemployeeOfShiftListShift = shiftListShift.getEmployeeIdemployee();
                shiftListShift.setEmployeeIdemployee(employee);
                shiftListShift = em.merge(shiftListShift);
                if (oldEmployeeIdemployeeOfShiftListShift != null) {
                    oldEmployeeIdemployeeOfShiftListShift.getShiftList().remove(shiftListShift);
                    oldEmployeeIdemployeeOfShiftListShift = em.merge(oldEmployeeIdemployeeOfShiftListShift);
                }
            }
            for (Tax taxListTax : employee.getTaxList()) {
                Employee oldEmployeeIdemployeeOfTaxListTax = taxListTax.getEmployeeIdemployee();
                taxListTax.setEmployeeIdemployee(employee);
                taxListTax = em.merge(taxListTax);
                if (oldEmployeeIdemployeeOfTaxListTax != null) {
                    oldEmployeeIdemployeeOfTaxListTax.getTaxList().remove(taxListTax);
                    oldEmployeeIdemployeeOfTaxListTax = em.merge(oldEmployeeIdemployeeOfTaxListTax);
                }
            }
            for (Salary salaryListSalary : employee.getSalaryList()) {
                Employee oldEmployeeIdemployeeOfSalaryListSalary = salaryListSalary.getEmployeeIdemployee();
                salaryListSalary.setEmployeeIdemployee(employee);
                salaryListSalary = em.merge(salaryListSalary);
                if (oldEmployeeIdemployeeOfSalaryListSalary != null) {
                    oldEmployeeIdemployeeOfSalaryListSalary.getSalaryList().remove(salaryListSalary);
                    oldEmployeeIdemployeeOfSalaryListSalary = em.merge(oldEmployeeIdemployeeOfSalaryListSalary);
                }
            }
            for (Bank bankListBank : employee.getBankList()) {
                Employee oldEmployeeIdemployeeOfBankListBank = bankListBank.getEmployeeIdemployee();
                bankListBank.setEmployeeIdemployee(employee);
                bankListBank = em.merge(bankListBank);
                if (oldEmployeeIdemployeeOfBankListBank != null) {
                    oldEmployeeIdemployeeOfBankListBank.getBankList().remove(bankListBank);
                    oldEmployeeIdemployeeOfBankListBank = em.merge(oldEmployeeIdemployeeOfBankListBank);
                }
            }
            for (PerformanceFeedback performanceFeedbackListPerformanceFeedback : employee.getPerformanceFeedbackList()) {
                Employee oldEmployeeIdemployeeOfPerformanceFeedbackListPerformanceFeedback = performanceFeedbackListPerformanceFeedback.getEmployeeIdemployee();
                performanceFeedbackListPerformanceFeedback.setEmployeeIdemployee(employee);
                performanceFeedbackListPerformanceFeedback = em.merge(performanceFeedbackListPerformanceFeedback);
                if (oldEmployeeIdemployeeOfPerformanceFeedbackListPerformanceFeedback != null) {
                    oldEmployeeIdemployeeOfPerformanceFeedbackListPerformanceFeedback.getPerformanceFeedbackList().remove(performanceFeedbackListPerformanceFeedback);
                    oldEmployeeIdemployeeOfPerformanceFeedbackListPerformanceFeedback = em.merge(oldEmployeeIdemployeeOfPerformanceFeedbackListPerformanceFeedback);
                }
            }
            for (Job jobListJob : employee.getJobList()) {
                Employee oldEmployeeIdemployeeOfJobListJob = jobListJob.getEmployeeIdemployee();
                jobListJob.setEmployeeIdemployee(employee);
                jobListJob = em.merge(jobListJob);
                if (oldEmployeeIdemployeeOfJobListJob != null) {
                    oldEmployeeIdemployeeOfJobListJob.getJobList().remove(jobListJob);
                    oldEmployeeIdemployeeOfJobListJob = em.merge(oldEmployeeIdemployeeOfJobListJob);
                }
            }
            for (TimeOffRequest timeOffRequestListTimeOffRequest : employee.getTimeOffRequestList()) {
                Employee oldEmployeeIdemployeeOfTimeOffRequestListTimeOffRequest = timeOffRequestListTimeOffRequest.getEmployeeIdemployee();
                timeOffRequestListTimeOffRequest.setEmployeeIdemployee(employee);
                timeOffRequestListTimeOffRequest = em.merge(timeOffRequestListTimeOffRequest);
                if (oldEmployeeIdemployeeOfTimeOffRequestListTimeOffRequest != null) {
                    oldEmployeeIdemployeeOfTimeOffRequestListTimeOffRequest.getTimeOffRequestList().remove(timeOffRequestListTimeOffRequest);
                    oldEmployeeIdemployeeOfTimeOffRequestListTimeOffRequest = em.merge(oldEmployeeIdemployeeOfTimeOffRequestListTimeOffRequest);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee persistentEmployee = em.find(Employee.class, employee.getIdemployee());
            List<Insurance> insuranceListOld = persistentEmployee.getInsuranceList();
            List<Insurance> insuranceListNew = employee.getInsuranceList();
            List<DisciplinaryHistory> disciplinaryHistoryListOld = persistentEmployee.getDisciplinaryHistoryList();
            List<DisciplinaryHistory> disciplinaryHistoryListNew = employee.getDisciplinaryHistoryList();
            List<Shift> shiftListOld = persistentEmployee.getShiftList();
            List<Shift> shiftListNew = employee.getShiftList();
            List<Tax> taxListOld = persistentEmployee.getTaxList();
            List<Tax> taxListNew = employee.getTaxList();
            List<Salary> salaryListOld = persistentEmployee.getSalaryList();
            List<Salary> salaryListNew = employee.getSalaryList();
            List<Bank> bankListOld = persistentEmployee.getBankList();
            List<Bank> bankListNew = employee.getBankList();
            List<PerformanceFeedback> performanceFeedbackListOld = persistentEmployee.getPerformanceFeedbackList();
            List<PerformanceFeedback> performanceFeedbackListNew = employee.getPerformanceFeedbackList();
            List<Job> jobListOld = persistentEmployee.getJobList();
            List<Job> jobListNew = employee.getJobList();
            List<TimeOffRequest> timeOffRequestListOld = persistentEmployee.getTimeOffRequestList();
            List<TimeOffRequest> timeOffRequestListNew = employee.getTimeOffRequestList();
            List<String> illegalOrphanMessages = null;
            for (Insurance insuranceListOldInsurance : insuranceListOld) {
                if (!insuranceListNew.contains(insuranceListOldInsurance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Insurance " + insuranceListOldInsurance + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (DisciplinaryHistory disciplinaryHistoryListOldDisciplinaryHistory : disciplinaryHistoryListOld) {
                if (!disciplinaryHistoryListNew.contains(disciplinaryHistoryListOldDisciplinaryHistory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DisciplinaryHistory " + disciplinaryHistoryListOldDisciplinaryHistory + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (Shift shiftListOldShift : shiftListOld) {
                if (!shiftListNew.contains(shiftListOldShift)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Shift " + shiftListOldShift + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (Tax taxListOldTax : taxListOld) {
                if (!taxListNew.contains(taxListOldTax)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tax " + taxListOldTax + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (Salary salaryListOldSalary : salaryListOld) {
                if (!salaryListNew.contains(salaryListOldSalary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Salary " + salaryListOldSalary + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (PerformanceFeedback performanceFeedbackListOldPerformanceFeedback : performanceFeedbackListOld) {
                if (!performanceFeedbackListNew.contains(performanceFeedbackListOldPerformanceFeedback)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PerformanceFeedback " + performanceFeedbackListOldPerformanceFeedback + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (Job jobListOldJob : jobListOld) {
                if (!jobListNew.contains(jobListOldJob)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Job " + jobListOldJob + " since its employeeIdemployee field is not nullable.");
                }
            }
            for (TimeOffRequest timeOffRequestListOldTimeOffRequest : timeOffRequestListOld) {
                if (!timeOffRequestListNew.contains(timeOffRequestListOldTimeOffRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TimeOffRequest " + timeOffRequestListOldTimeOffRequest + " since its employeeIdemployee field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Insurance> attachedInsuranceListNew = new ArrayList<Insurance>();
            for (Insurance insuranceListNewInsuranceToAttach : insuranceListNew) {
                insuranceListNewInsuranceToAttach = em.getReference(insuranceListNewInsuranceToAttach.getClass(), insuranceListNewInsuranceToAttach.getIdinsurance());
                attachedInsuranceListNew.add(insuranceListNewInsuranceToAttach);
            }
            insuranceListNew = attachedInsuranceListNew;
            employee.setInsuranceList(insuranceListNew);
            List<DisciplinaryHistory> attachedDisciplinaryHistoryListNew = new ArrayList<DisciplinaryHistory>();
            for (DisciplinaryHistory disciplinaryHistoryListNewDisciplinaryHistoryToAttach : disciplinaryHistoryListNew) {
                disciplinaryHistoryListNewDisciplinaryHistoryToAttach = em.getReference(disciplinaryHistoryListNewDisciplinaryHistoryToAttach.getClass(), disciplinaryHistoryListNewDisciplinaryHistoryToAttach.getIddisciplinaryHistory());
                attachedDisciplinaryHistoryListNew.add(disciplinaryHistoryListNewDisciplinaryHistoryToAttach);
            }
            disciplinaryHistoryListNew = attachedDisciplinaryHistoryListNew;
            employee.setDisciplinaryHistoryList(disciplinaryHistoryListNew);
            List<Shift> attachedShiftListNew = new ArrayList<Shift>();
            for (Shift shiftListNewShiftToAttach : shiftListNew) {
                shiftListNewShiftToAttach = em.getReference(shiftListNewShiftToAttach.getClass(), shiftListNewShiftToAttach.getIdshift());
                attachedShiftListNew.add(shiftListNewShiftToAttach);
            }
            shiftListNew = attachedShiftListNew;
            employee.setShiftList(shiftListNew);
            List<Tax> attachedTaxListNew = new ArrayList<Tax>();
            for (Tax taxListNewTaxToAttach : taxListNew) {
                taxListNewTaxToAttach = em.getReference(taxListNewTaxToAttach.getClass(), taxListNewTaxToAttach.getIdtax());
                attachedTaxListNew.add(taxListNewTaxToAttach);
            }
            taxListNew = attachedTaxListNew;
            employee.setTaxList(taxListNew);
            List<Salary> attachedSalaryListNew = new ArrayList<Salary>();
            for (Salary salaryListNewSalaryToAttach : salaryListNew) {
                salaryListNewSalaryToAttach = em.getReference(salaryListNewSalaryToAttach.getClass(), salaryListNewSalaryToAttach.getIdsalary());
                attachedSalaryListNew.add(salaryListNewSalaryToAttach);
            }
            salaryListNew = attachedSalaryListNew;
            employee.setSalaryList(salaryListNew);
            List<Bank> attachedBankListNew = new ArrayList<Bank>();
            for (Bank bankListNewBankToAttach : bankListNew) {
                bankListNewBankToAttach = em.getReference(bankListNewBankToAttach.getClass(), bankListNewBankToAttach.getIdbank());
                attachedBankListNew.add(bankListNewBankToAttach);
            }
            bankListNew = attachedBankListNew;
            employee.setBankList(bankListNew);
            List<PerformanceFeedback> attachedPerformanceFeedbackListNew = new ArrayList<PerformanceFeedback>();
            for (PerformanceFeedback performanceFeedbackListNewPerformanceFeedbackToAttach : performanceFeedbackListNew) {
                performanceFeedbackListNewPerformanceFeedbackToAttach = em.getReference(performanceFeedbackListNewPerformanceFeedbackToAttach.getClass(), performanceFeedbackListNewPerformanceFeedbackToAttach.getIdperformanceFeedback());
                attachedPerformanceFeedbackListNew.add(performanceFeedbackListNewPerformanceFeedbackToAttach);
            }
            performanceFeedbackListNew = attachedPerformanceFeedbackListNew;
            employee.setPerformanceFeedbackList(performanceFeedbackListNew);
            List<Job> attachedJobListNew = new ArrayList<Job>();
            for (Job jobListNewJobToAttach : jobListNew) {
                jobListNewJobToAttach = em.getReference(jobListNewJobToAttach.getClass(), jobListNewJobToAttach.getIdjob());
                attachedJobListNew.add(jobListNewJobToAttach);
            }
            jobListNew = attachedJobListNew;
            employee.setJobList(jobListNew);
            List<TimeOffRequest> attachedTimeOffRequestListNew = new ArrayList<TimeOffRequest>();
            for (TimeOffRequest timeOffRequestListNewTimeOffRequestToAttach : timeOffRequestListNew) {
                timeOffRequestListNewTimeOffRequestToAttach = em.getReference(timeOffRequestListNewTimeOffRequestToAttach.getClass(), timeOffRequestListNewTimeOffRequestToAttach.getIdtimeOffRequest());
                attachedTimeOffRequestListNew.add(timeOffRequestListNewTimeOffRequestToAttach);
            }
            timeOffRequestListNew = attachedTimeOffRequestListNew;
            employee.setTimeOffRequestList(timeOffRequestListNew);
            employee = em.merge(employee);
            for (Insurance insuranceListNewInsurance : insuranceListNew) {
                if (!insuranceListOld.contains(insuranceListNewInsurance)) {
                    Employee oldEmployeeIdemployeeOfInsuranceListNewInsurance = insuranceListNewInsurance.getEmployeeIdemployee();
                    insuranceListNewInsurance.setEmployeeIdemployee(employee);
                    insuranceListNewInsurance = em.merge(insuranceListNewInsurance);
                    if (oldEmployeeIdemployeeOfInsuranceListNewInsurance != null && !oldEmployeeIdemployeeOfInsuranceListNewInsurance.equals(employee)) {
                        oldEmployeeIdemployeeOfInsuranceListNewInsurance.getInsuranceList().remove(insuranceListNewInsurance);
                        oldEmployeeIdemployeeOfInsuranceListNewInsurance = em.merge(oldEmployeeIdemployeeOfInsuranceListNewInsurance);
                    }
                }
            }
            for (DisciplinaryHistory disciplinaryHistoryListNewDisciplinaryHistory : disciplinaryHistoryListNew) {
                if (!disciplinaryHistoryListOld.contains(disciplinaryHistoryListNewDisciplinaryHistory)) {
                    Employee oldEmployeeIdemployeeOfDisciplinaryHistoryListNewDisciplinaryHistory = disciplinaryHistoryListNewDisciplinaryHistory.getEmployeeIdemployee();
                    disciplinaryHistoryListNewDisciplinaryHistory.setEmployeeIdemployee(employee);
                    disciplinaryHistoryListNewDisciplinaryHistory = em.merge(disciplinaryHistoryListNewDisciplinaryHistory);
                    if (oldEmployeeIdemployeeOfDisciplinaryHistoryListNewDisciplinaryHistory != null && !oldEmployeeIdemployeeOfDisciplinaryHistoryListNewDisciplinaryHistory.equals(employee)) {
                        oldEmployeeIdemployeeOfDisciplinaryHistoryListNewDisciplinaryHistory.getDisciplinaryHistoryList().remove(disciplinaryHistoryListNewDisciplinaryHistory);
                        oldEmployeeIdemployeeOfDisciplinaryHistoryListNewDisciplinaryHistory = em.merge(oldEmployeeIdemployeeOfDisciplinaryHistoryListNewDisciplinaryHistory);
                    }
                }
            }
            for (Shift shiftListNewShift : shiftListNew) {
                if (!shiftListOld.contains(shiftListNewShift)) {
                    Employee oldEmployeeIdemployeeOfShiftListNewShift = shiftListNewShift.getEmployeeIdemployee();
                    shiftListNewShift.setEmployeeIdemployee(employee);
                    shiftListNewShift = em.merge(shiftListNewShift);
                    if (oldEmployeeIdemployeeOfShiftListNewShift != null && !oldEmployeeIdemployeeOfShiftListNewShift.equals(employee)) {
                        oldEmployeeIdemployeeOfShiftListNewShift.getShiftList().remove(shiftListNewShift);
                        oldEmployeeIdemployeeOfShiftListNewShift = em.merge(oldEmployeeIdemployeeOfShiftListNewShift);
                    }
                }
            }
            for (Tax taxListNewTax : taxListNew) {
                if (!taxListOld.contains(taxListNewTax)) {
                    Employee oldEmployeeIdemployeeOfTaxListNewTax = taxListNewTax.getEmployeeIdemployee();
                    taxListNewTax.setEmployeeIdemployee(employee);
                    taxListNewTax = em.merge(taxListNewTax);
                    if (oldEmployeeIdemployeeOfTaxListNewTax != null && !oldEmployeeIdemployeeOfTaxListNewTax.equals(employee)) {
                        oldEmployeeIdemployeeOfTaxListNewTax.getTaxList().remove(taxListNewTax);
                        oldEmployeeIdemployeeOfTaxListNewTax = em.merge(oldEmployeeIdemployeeOfTaxListNewTax);
                    }
                }
            }
            for (Salary salaryListNewSalary : salaryListNew) {
                if (!salaryListOld.contains(salaryListNewSalary)) {
                    Employee oldEmployeeIdemployeeOfSalaryListNewSalary = salaryListNewSalary.getEmployeeIdemployee();
                    salaryListNewSalary.setEmployeeIdemployee(employee);
                    salaryListNewSalary = em.merge(salaryListNewSalary);
                    if (oldEmployeeIdemployeeOfSalaryListNewSalary != null && !oldEmployeeIdemployeeOfSalaryListNewSalary.equals(employee)) {
                        oldEmployeeIdemployeeOfSalaryListNewSalary.getSalaryList().remove(salaryListNewSalary);
                        oldEmployeeIdemployeeOfSalaryListNewSalary = em.merge(oldEmployeeIdemployeeOfSalaryListNewSalary);
                    }
                }
            }
            for (Bank bankListOldBank : bankListOld) {
                if (!bankListNew.contains(bankListOldBank)) {
                    bankListOldBank.setEmployeeIdemployee(null);
                    bankListOldBank = em.merge(bankListOldBank);
                }
            }
            for (Bank bankListNewBank : bankListNew) {
                if (!bankListOld.contains(bankListNewBank)) {
                    Employee oldEmployeeIdemployeeOfBankListNewBank = bankListNewBank.getEmployeeIdemployee();
                    bankListNewBank.setEmployeeIdemployee(employee);
                    bankListNewBank = em.merge(bankListNewBank);
                    if (oldEmployeeIdemployeeOfBankListNewBank != null && !oldEmployeeIdemployeeOfBankListNewBank.equals(employee)) {
                        oldEmployeeIdemployeeOfBankListNewBank.getBankList().remove(bankListNewBank);
                        oldEmployeeIdemployeeOfBankListNewBank = em.merge(oldEmployeeIdemployeeOfBankListNewBank);
                    }
                }
            }
            for (PerformanceFeedback performanceFeedbackListNewPerformanceFeedback : performanceFeedbackListNew) {
                if (!performanceFeedbackListOld.contains(performanceFeedbackListNewPerformanceFeedback)) {
                    Employee oldEmployeeIdemployeeOfPerformanceFeedbackListNewPerformanceFeedback = performanceFeedbackListNewPerformanceFeedback.getEmployeeIdemployee();
                    performanceFeedbackListNewPerformanceFeedback.setEmployeeIdemployee(employee);
                    performanceFeedbackListNewPerformanceFeedback = em.merge(performanceFeedbackListNewPerformanceFeedback);
                    if (oldEmployeeIdemployeeOfPerformanceFeedbackListNewPerformanceFeedback != null && !oldEmployeeIdemployeeOfPerformanceFeedbackListNewPerformanceFeedback.equals(employee)) {
                        oldEmployeeIdemployeeOfPerformanceFeedbackListNewPerformanceFeedback.getPerformanceFeedbackList().remove(performanceFeedbackListNewPerformanceFeedback);
                        oldEmployeeIdemployeeOfPerformanceFeedbackListNewPerformanceFeedback = em.merge(oldEmployeeIdemployeeOfPerformanceFeedbackListNewPerformanceFeedback);
                    }
                }
            }
            for (Job jobListNewJob : jobListNew) {
                if (!jobListOld.contains(jobListNewJob)) {
                    Employee oldEmployeeIdemployeeOfJobListNewJob = jobListNewJob.getEmployeeIdemployee();
                    jobListNewJob.setEmployeeIdemployee(employee);
                    jobListNewJob = em.merge(jobListNewJob);
                    if (oldEmployeeIdemployeeOfJobListNewJob != null && !oldEmployeeIdemployeeOfJobListNewJob.equals(employee)) {
                        oldEmployeeIdemployeeOfJobListNewJob.getJobList().remove(jobListNewJob);
                        oldEmployeeIdemployeeOfJobListNewJob = em.merge(oldEmployeeIdemployeeOfJobListNewJob);
                    }
                }
            }
            for (TimeOffRequest timeOffRequestListNewTimeOffRequest : timeOffRequestListNew) {
                if (!timeOffRequestListOld.contains(timeOffRequestListNewTimeOffRequest)) {
                    Employee oldEmployeeIdemployeeOfTimeOffRequestListNewTimeOffRequest = timeOffRequestListNewTimeOffRequest.getEmployeeIdemployee();
                    timeOffRequestListNewTimeOffRequest.setEmployeeIdemployee(employee);
                    timeOffRequestListNewTimeOffRequest = em.merge(timeOffRequestListNewTimeOffRequest);
                    if (oldEmployeeIdemployeeOfTimeOffRequestListNewTimeOffRequest != null && !oldEmployeeIdemployeeOfTimeOffRequestListNewTimeOffRequest.equals(employee)) {
                        oldEmployeeIdemployeeOfTimeOffRequestListNewTimeOffRequest.getTimeOffRequestList().remove(timeOffRequestListNewTimeOffRequest);
                        oldEmployeeIdemployeeOfTimeOffRequestListNewTimeOffRequest = em.merge(oldEmployeeIdemployeeOfTimeOffRequestListNewTimeOffRequest);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = employee.getIdemployee();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getIdemployee();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Insurance> insuranceListOrphanCheck = employee.getInsuranceList();
            for (Insurance insuranceListOrphanCheckInsurance : insuranceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Insurance " + insuranceListOrphanCheckInsurance + " in its insuranceList field has a non-nullable employeeIdemployee field.");
            }
            List<DisciplinaryHistory> disciplinaryHistoryListOrphanCheck = employee.getDisciplinaryHistoryList();
            for (DisciplinaryHistory disciplinaryHistoryListOrphanCheckDisciplinaryHistory : disciplinaryHistoryListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the DisciplinaryHistory " + disciplinaryHistoryListOrphanCheckDisciplinaryHistory + " in its disciplinaryHistoryList field has a non-nullable employeeIdemployee field.");
            }
            List<Shift> shiftListOrphanCheck = employee.getShiftList();
            for (Shift shiftListOrphanCheckShift : shiftListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Shift " + shiftListOrphanCheckShift + " in its shiftList field has a non-nullable employeeIdemployee field.");
            }
            List<Tax> taxListOrphanCheck = employee.getTaxList();
            for (Tax taxListOrphanCheckTax : taxListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Tax " + taxListOrphanCheckTax + " in its taxList field has a non-nullable employeeIdemployee field.");
            }
            List<Salary> salaryListOrphanCheck = employee.getSalaryList();
            for (Salary salaryListOrphanCheckSalary : salaryListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Salary " + salaryListOrphanCheckSalary + " in its salaryList field has a non-nullable employeeIdemployee field.");
            }
            List<PerformanceFeedback> performanceFeedbackListOrphanCheck = employee.getPerformanceFeedbackList();
            for (PerformanceFeedback performanceFeedbackListOrphanCheckPerformanceFeedback : performanceFeedbackListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the PerformanceFeedback " + performanceFeedbackListOrphanCheckPerformanceFeedback + " in its performanceFeedbackList field has a non-nullable employeeIdemployee field.");
            }
            List<Job> jobListOrphanCheck = employee.getJobList();
            for (Job jobListOrphanCheckJob : jobListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Job " + jobListOrphanCheckJob + " in its jobList field has a non-nullable employeeIdemployee field.");
            }
            List<TimeOffRequest> timeOffRequestListOrphanCheck = employee.getTimeOffRequestList();
            for (TimeOffRequest timeOffRequestListOrphanCheckTimeOffRequest : timeOffRequestListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the TimeOffRequest " + timeOffRequestListOrphanCheckTimeOffRequest + " in its timeOffRequestList field has a non-nullable employeeIdemployee field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Bank> bankList = employee.getBankList();
            for (Bank bankListBank : bankList) {
                bankListBank.setEmployeeIdemployee(null);
                bankListBank = em.merge(bankListBank);
            }
            em.remove(employee);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee findEmployee(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
