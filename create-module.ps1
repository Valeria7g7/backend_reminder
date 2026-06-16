param(
    [string]$ModuleName
)

$basePath = "src/main/java/com/valeria/backend/modules/$ModuleName"

$folders = @(
    "controller",
    "service",
    "repository",
    "entity",
    "dto",
    "mapper"
)

foreach ($folder in $folders) {
    New-Item -ItemType Directory -Path "$basePath/$folder" -Force
}

$ClassName = (Get-Culture).TextInfo.ToTitleCase($ModuleName)

@"
package com.valeria.backend.modules.$ModuleName.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/$ModuleName")
public class ${ClassName}Controller {

}
"@ | Set-Content "$basePath/controller/${ClassName}Controller.java"

@"
package com.valeria.backend.modules.$ModuleName.service;

import org.springframework.stereotype.Service;

@Service
public class ${ClassName}Service {

}
"@ | Set-Content "$basePath/service/${ClassName}Service.java"

@"
package com.valeria.backend.modules.$ModuleName.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.valeria.backend.modules.$ModuleName.entity.${ClassName};

public interface ${ClassName}Repository extends JpaRepository<${ClassName}, Long> {

}
"@ | Set-Content "$basePath/repository/${ClassName}Repository.java"

@"
package com.valeria.backend.modules.$ModuleName.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ${ClassName} {

    @Id
    private Long id;

}
"@ | Set-Content "$basePath/entity/${ClassName}.java"

Write-Host "Modulo $ModuleName creado correctamente"
