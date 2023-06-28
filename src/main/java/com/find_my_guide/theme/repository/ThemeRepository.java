package com.find_my_guide.theme.repository;

import com.find_my_guide.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
}
