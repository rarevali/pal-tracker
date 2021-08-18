package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;



    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }



    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {


        return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }




    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntryRead = timeEntryRepository.find(timeEntryId);

        if (timeEntryRead == null)
            return new ResponseEntity<>(timeEntryRead, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(timeEntryRead, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntryList, HttpStatus.OK);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,
                                            @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntryUpdate =  timeEntryRepository.update(timeEntryId,timeEntryToUpdate);

        if (timeEntryUpdate == null)
            return new ResponseEntity<>(timeEntryUpdate, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(timeEntryUpdate, HttpStatus.OK);

    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
