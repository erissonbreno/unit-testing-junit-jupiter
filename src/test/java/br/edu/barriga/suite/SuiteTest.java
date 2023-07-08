package br.edu.barriga.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite de Testes")
@SelectPackages(value = {
        "br.edu.barriga.service",
        "br.edu.barriga.domain"
})
public class SuiteTest {
}
