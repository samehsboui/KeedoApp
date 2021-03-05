package tn.esprit.pi.services;

import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.UnhealthyWord;

import java.util.List;

@Service
public interface IUnhealthyWordService {
    void removeWord(String word);

    boolean wordExists(String word);

    //void addWord(UnhealthyWord u, String word);

    void deleteWord(String word);

    List<UnhealthyWord> getUnhealthyWordList();

	void addWord(UnhealthyWord u);
}
