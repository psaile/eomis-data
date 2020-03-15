package org.eomis.data;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.eomis.data");

        noClasses()
            .that()
            .resideInAnyPackage("org.eomis.data.service..")
            .or()
            .resideInAnyPackage("org.eomis.data.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.eomis.data.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
