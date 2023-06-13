package com;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/conge/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping("/Rh")
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();

        //return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/Rh/{id}")
    public LeaveRequest getLeaveRequestById(@PathVariable Long id)  {
        return leaveRequestService.getLeaveRequestById(id);
    }

    @PostMapping("/employer")
    public LeaveRequest createLeaveRequest(@Valid @RequestBody LeaveRequest leaveRequest/*,@PathVariable("employeeId") Long employeeId*/) {
        return leaveRequestService.createLeaveRequest(leaveRequest);
    }

    @PutMapping("/employer/{id}")
    public LeaveRequest updateLeaveRequest(@PathVariable Long id, @Valid @RequestBody LeaveRequest leaveRequestDetails) {
        return leaveRequestService.updateLeaveRequest(id, leaveRequestDetails);
    }

    @DeleteMapping("/employer/{id}")
    public ResponseEntity<?> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/employer/{id}/approve")
    public LeaveRequest approveLeaveRequest(@PathVariable long id) {
        return leaveRequestService.approveLeaveRequest(id);
    }

    @PutMapping("/employer/{id}/reject")
    public LeaveRequest rejectLeaveRequest(@PathVariable Long id) {
        return leaveRequestService.rejectLeaveRequest(id);
    }
}
