package com.vote.service.impl;

import com.vote.model.Vote;
import com.vote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class VoteServiceImpl {

    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    @CrossOrigin
    @PostMapping(path = "/api/createVotes", consumes = "application/json")
    public void createVotes(@RequestBody List<String> voteNames) {

        String voter = getVoter();
        deleteVotesIfExists(voter);
        addVotes(voteNames, voter);
    }

    @CrossOrigin
    @GetMapping(path = "/api/getVotes", produces = "application/json")
    public List<String> getVotes() {

        String voter = getVoter();

        List<Vote> votes = voteRepository.findAllByVoterAndIsDeleted(voter,false);
        List<String> voteString = new ArrayList<>();
        for (Vote vote : votes) {
            voteString.add(vote.getSelection());
        }
        voteString.add("C4");
        return voteString;
    }

    @CrossOrigin
    @GetMapping(path = "/api/getVotesCountGroupBySelection", produces = "application/json")
    public List<?> getVotesCountGroupBySelection() {
       return voteRepository.getVotesCountGroupBySelection();
    }

    @CrossOrigin
    @org.springframework.transaction.annotation.Transactional
    @GetMapping(path = "/api/isConnect")
    public String isConnect() {
        return "connection is successful";
    }

    private String getVoter() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        if (request == null)
            return "";

        return request.getRemoteAddr();
    }

    private void addVotes(@RequestBody List<String> voteNames, String ip) {
        for (String innerName : voteNames) {
            voteRepository.save(new Vote(ip, new Date(), innerName));
        }
    }

    private void deleteVotesIfExists(String ip) {
        voteRepository.updateIsDeleted(ip);
    }
}
