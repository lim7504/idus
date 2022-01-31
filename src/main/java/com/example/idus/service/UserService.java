package com.example.idus.service;

import com.example.idus.config.Code;
import com.example.idus.config.IdusException;
import com.example.idus.domain.User;
import com.example.idus.domain.dto.GetUserDto;
import com.example.idus.domain.dto.JoinUserDto;
import com.example.idus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Transactional
    public void joinUser(JoinUserDto joinUserDto) {
        this.checkDuplicationEmail(joinUserDto.getEmail());
        joinUserDto.setPassword(this.bCryptPasswordEncoder.encode(joinUserDto.getPassword()));
        User user = User.createUser(joinUserDto);
        this.userRepository.save(user);
    }

    // 유저 상세 조회
    @Transactional(readOnly = true)
    public GetUserDto getUserDetail(Long userId) {
        User user = this.getUser(userId);
        return user.getUserDto();
    }

    // 유저 리스트 조회
    @Transactional(readOnly = true)
    public Page<GetUserDto> getUserDtoPageList(String name, String email, Pageable pageable) {
        PageImpl<User> userList = this.getUserPageList(name, email, pageable);
        return userList.map(User::getUserDtoForList);
    }


    private PageImpl<User> getUserPageList(String name, String email, Pageable pageable) {
        return this.userRepository.findAllByCondition(name, email, pageable);
    }

    private User getUser(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new IdusException(Code.ACCOUNT_NOT_FOUND));
    }

    private void checkDuplicationEmail(String email) {
        boolean isExistName = this.userRepository.existsByEmail(email);
        if(isExistName) {
            throw new IdusException(Code.EMAIL_DUPLICATE);
        }
    }

}
