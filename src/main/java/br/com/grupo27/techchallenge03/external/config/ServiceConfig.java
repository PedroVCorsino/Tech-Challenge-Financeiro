package br.com.grupo27.techchallenge03.external.config;

import java.net.MalformedURLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.grupo27.techchallenge03.adapters.gateways.PagamentoGateway;

import br.com.grupo27.techchallenge03.adapters.mappers.PagamentoMapper;

import br.com.grupo27.techchallenge03.application.usecases.PagamentoUseCaseImpl;

import br.com.grupo27.techchallenge03.application.usecases.pix.PixConsultaPagamentoUseCaseImpl;
import br.com.grupo27.techchallenge03.application.usecases.pix.PixGeraCobrancaUseCaseImpl;
import br.com.grupo27.techchallenge03.application.usecases.pix.PixGeraQRCodeUseCaseImpl;
import br.com.grupo27.techchallenge03.application.usecases.pix.PixUseCaseImpl;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.PagamentoUsecase;

import br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix.PixConsultaCobrancaUseCase;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix.PixGeraCobrancaUseCase;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix.PixGeraQRCodeUseCase;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix.PixUseCase;
import br.com.grupo27.techchallenge03.external.security.JwtTokenFilter;

@Configuration
public class ServiceConfig {

    @Bean
    public PixUseCaseImpl pixUseCase(PixGeraCobrancaUseCase pixCobranca, PixGeraQRCodeUseCase pixQrCode, PixConsultaCobrancaUseCase pixConsulta) {
        return new PixUseCaseImpl(pixCobranca, pixQrCode, pixConsulta);
    }

    @Bean 
    public PagamentoUseCaseImpl pagamentoUseCase(PixUseCase pix, PagamentoGateway pagamentoGateway, PagamentoMapper pagamentoMapper){
        return new PagamentoUseCaseImpl(pix, pagamentoGateway, pagamentoMapper);
    }

    @Bean
    public PixConsultaPagamentoUseCaseImpl pixConsultaPagamentoUseCase(Credenciais credentials){
        return new PixConsultaPagamentoUseCaseImpl(credentials);
    }

    @Bean
    public PixGeraQRCodeUseCaseImpl pixGeraQRCodeUseCase(Credenciais credentials){
        return new PixGeraQRCodeUseCaseImpl(credentials);
    }

    @Bean
    public PixGeraCobrancaUseCaseImpl pixGeraCobrancaUseCase(Credenciais credentials){
        return new PixGeraCobrancaUseCaseImpl(credentials);
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        try {
            return new JwtTokenFilter();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
