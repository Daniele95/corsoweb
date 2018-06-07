package com.corsojava.sakilajpa.dao;

import com.corsojava.sakilajpa.model.Language;
import java.util.List;

/**
 *
 * @author <a href="mailto:lucio.benfante@gmail.com">Lucio Benfante</a>
 */
public interface LanguageDao {
    List<Language> getAllLanguages();
    Language getLanguage(Short id);
}
