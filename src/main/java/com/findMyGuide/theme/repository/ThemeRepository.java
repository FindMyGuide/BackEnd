package com.findMyGuide.theme.repository;

import com.findMyGuide.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
}
