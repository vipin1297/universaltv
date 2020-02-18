package com.geekyants.ads.dto;

import java.util.List;

public class SlotRequestDto {

	private List<SelectedSlot> selectedSlots;

	public List<SelectedSlot> getSelectedSlots() {
		return selectedSlots;
	}

	public void setSelectedSlots(List<SelectedSlot> selectedSlots) {
		this.selectedSlots = selectedSlots;
	}
}
