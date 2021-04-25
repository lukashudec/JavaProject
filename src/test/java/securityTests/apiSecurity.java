package securityTests;

/*
https://github.com/shieldfy/API-Security-Checklist
https://github.com/OWASP/API-Security
*/

public interface apiSecurity {
    void malformed_xml();
    void sql_injection();
    void xpath_injection();
    void cross_site_scripting();
    void fuzzing_scan();
    void xml_bomb();
    void invalid_types();
    void boundary_scan();
}
