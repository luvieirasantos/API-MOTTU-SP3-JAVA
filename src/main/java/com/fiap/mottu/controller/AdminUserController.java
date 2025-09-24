package com.fiap.mottu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fiap.mottu.entity.MottuUsuario;
import com.fiap.mottu.entity.PerfilUsuario;
import com.fiap.mottu.service.MottuUsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private MottuUsuarioService usuarioService;

    @GetMapping
    public String list(Model model) {
        List<MottuUsuario> users = usuarioService.listarTodos();
        model.addAttribute("users", users);
        return "admin-users-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new MottuUsuario());
        model.addAttribute("perfis", PerfilUsuario.values());
        return "admin-users-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("user") MottuUsuario user,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("perfis", PerfilUsuario.values());
            return "admin-users-form";
        }
        try {
            usuarioService.criarUsuarioAdmin(user);
            redirectAttributes.addFlashAttribute("success", "Usuário criado com sucesso.");
            return "redirect:/admin/users";
        } catch (Exception ex) {
            model.addAttribute("perfis", PerfilUsuario.values());
            model.addAttribute("error", ex.getMessage());
            return "admin-users-form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        MottuUsuario user = usuarioService.buscarPorId(id);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Usuário não encontrado.");
            return "redirect:/admin/users";
        }
        // Não expor a senha no form
        user.setSenha("");
        model.addAttribute("user", user);
        model.addAttribute("perfis", PerfilUsuario.values());
        return "admin-users-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("user") MottuUsuario user,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("perfis", PerfilUsuario.values());
            return "admin-users-form";
        }
        try {
            // Se senha vier vazia, não altera
            if (!StringUtils.hasText(user.getSenha())) {
                usuarioService.atualizarDadosBasicos(id, user.getNome(), user.getEmail(), user.getPerfil(), user.getAtivo());
            } else {
                usuarioService.atualizarComSenha(id, user.getNome(), user.getEmail(), user.getPerfil(), user.getAtivo(), user.getSenha());
            }
            redirectAttributes.addFlashAttribute("success", "Usuário atualizado com sucesso.");
            return "redirect:/admin/users";
        } catch (Exception ex) {
            model.addAttribute("perfis", PerfilUsuario.values());
            model.addAttribute("error", ex.getMessage());
            return "admin-users-form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.excluirPorId(id);
        redirectAttributes.addFlashAttribute("success", "Usuário excluído com sucesso.");
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.alternarStatusAtivo(id);
        redirectAttributes.addFlashAttribute("success", "Status do usuário atualizado.");
        return "redirect:/admin/users";
    }
}



