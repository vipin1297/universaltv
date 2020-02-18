package com.geekyants.ads.service;

import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.dto.SlotRequestDto;
import com.geekyants.ads.dto.SlotResponseDto;

public interface SlotService {

	ResponseDto bookSlot(Long planId,SlotRequestDto slotRequest);

	SlotResponseDto getSlot(Long planId);

}
