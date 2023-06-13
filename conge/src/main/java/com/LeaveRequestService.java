package com;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;


    //La méthode getAllLeaveRequests() permet de récupérer toutes les demandes de congé via
    // une requête HTTP GET sur /api/leave-requests

    public List<LeaveRequest> getAllLeaveRequests() {
//        List<LeaveRequest> leaveRequestList = new ArrayList<>();
//        leaveRequestRepository.findAll().forEach(leaveRequestList::add);
//        return leaveRequestList;
        System.out.println("ùùùùùùùùùùùùùùùùùùùùùùùùùù");
        return leaveRequestRepository.findAll();
    }
    // La méthode getLeaveRequestById(Long id) permet de récupérer une demande de congé spécifique en utilisant
    //son identifiant via une requête HTTP GET sur /api/leave-requests/{id}

    public LeaveRequest getLeaveRequestById(Long id)  {
        return leaveRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Leave request"+ "id"+ id));
    }
    //La méthode createLeaveRequest(LeaveRequest leaveRequest) permet de créer une nouvelle
    // demande de congé via une requête HTTP POST sur /api/leave-requests

    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest/*, Long employeeId*/) {
        //Employee employee = employeService.getEmployeeById(employeeId);
        //leaveRequest.setEmployee(employee);
        return leaveRequestRepository.save(leaveRequest);
    }

    //    public LocationEntity saveLocation(LocationEntity location,
//                                       int clientId, int voitureId){
//        ClientEntity client = clientService.getClientById(clientId);
//        VoitureEntity voiture = voitureService.getVoitureById(voitureId);
//        location.setClient(client);
//        location.setVoiture(voiture);
//        return locationRepository.save(location);
//    }
    //La méthode updateLeaveRequest(Long id, LeaveRequest leaveRequestDetails)
    // permet de mettre à jour une demande de congé existante
    // en utilisant son identifiant via une requête HTTP PUT sur /api/leave-requests/{id}
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequestDetails) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Leave request"+ "id"+id));

        leaveRequest.setStartDate(leaveRequestDetails.getStartDate());
        leaveRequest.setEndDate(leaveRequestDetails.getEndDate());
        leaveRequest.setReason(leaveRequestDetails.getReason());
        leaveRequest.setStatus(leaveRequestDetails.getStatus());


        return leaveRequestRepository.save(leaveRequest);
    }
    //La méthode deleteLeaveRequest(Long id) permet de supprimer une demande de
    // congé existante en utilisant son
    // identifiant via une requête HTTP DELETE sur /api/leave-requests/{id}

    public void deleteLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Leave request"+ "id"+id));
        leaveRequestRepository.delete(leaveRequest);
    }

    //La méthode approveLeaveRequest(Long id) permet d'approuver une demande
    // de congé existante en utilisant son identifiant via une requête HTTP
    // PUT sur /api/leave-requests/{id}/approve
    public LeaveRequest approveLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequest.setStatus(LeaveRequestStatus.APPROVED);
        return leaveRequestRepository.save(leaveRequest);
    }

    //La méthode rejectLeaveRequest(Long id) permet de refuser une demande
    // de congé existante en utilisant son
    // identifiant via une requête HTTP PUT sur /api/leave-requests/{id}/reject
    public LeaveRequest rejectLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequest.setStatus(LeaveRequestStatus.REJECTED);
        return leaveRequestRepository.save(leaveRequest);
    }
}